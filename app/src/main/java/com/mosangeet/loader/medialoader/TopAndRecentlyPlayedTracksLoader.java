/*
* Copyright (C) 2014 The CyanogenMod Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.mosangeet.loader.medialoader;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldt.musicr.model.Song;
import com.ldt.musicr.provider.HistoryStore;
import com.ldt.musicr.provider.SongPlayCountStore;
import com.ldt.musicr.util.PreferenceUtil;

import java.util.ArrayList;

public class TopAndRecentlyPlayedTracksLoader {
    public static final int NUMBER_OF_TOP_TRACKS = 100;

    @NonNull
    public static ArrayList<Song> getRecentlyPlayedTracks(@NonNull Context context) {
        return SongLoader.getSongs(makeRecentTracksCursorAndClearUpDatabase(context));
    }

    @NonNull
    public static ArrayList<Song> getNotRecentlyPlayedTracks
(@NonNull Context context) {
        ArrayList<Song> allSongs = SongLoader.getSongs(
            SongLoader.makeSongCursor(
                context,
                null, null,
                MediaStore.Audio.Media.DATE_ADDED + " ASC"));

        ArrayList<Song> recentlyPlayedSongs = SongLoader.getSongs(
            makeRecentTracksCursorAndClearUpDatabase(context));

        allSongs.removeAll(recentlyPlayedSongs);

        return allSongs;
    }

    @NonNull
    public static ArrayList<Song> getTopTracks(@NonNull Context context) {
        return SongLoader.getSongs(makeTopTracksCursorAndClearUpDatabase(context));
    }

    @Nullable
    public static Cursor makeRecentTracksCursorAndClearUpDatabase(@NonNull final Context context) {
        SortedLongCursor retCursor = makeRecentTracksCursorImpl(context);

        // clean up the databases with any ids not found
        if (retCursor != null) {
            ArrayList<Long> missingIds = retCursor.getMissingIds();
            if (missingIds != null && missingIds.size() > 0) {
                for (long id : missingIds) {
                    HistoryStore.getInstance(context).removeSongId(id);
                }
            }
        }
        return retCursor;
    }

    @Nullable
    public static Cursor makeTopTracksCursorAndClearUpDatabase(@NonNull final Context context) {
        SortedLongCursor retCursor = makeTopTracksCursorImpl(context);

        // clean up the databases with any ids not found
        if (retCursor != null) {
            ArrayList<Long> missingIds = retCursor.getMissingIds();
            if (missingIds != null && missingIds.size() > 0) {
                for (long id : missingIds) {
                    SongPlayCountStore.getInstance(context).removeItem(id);
                }
            }
        }
        return retCursor;
    }

    @Nullable
    private static SortedLongCursor makeRecentTracksCursorImpl(@NonNull final Context context) {
        // first get the top results ids from the internal database
        final long cutoff = PreferenceUtil.getInstance(context).getRecentlyPlayedCutoffTimeMillis();
        Cursor songs = HistoryStore.getInstance(context).queryRecentIds(cutoff);

        try {
            return makeSortedCursor(context, songs,
                    songs.getColumnIndex(HistoryStore.RecentStoreColumns.ID));
        } finally {
            if (songs != null) {
                songs.close();
            }
        }
    }

    @Nullable
    private static SortedLongCursor makeTopTracksCursorImpl(@NonNull final Context context) {
        // first get the top results ids from the internal database
        Cursor songs = SongPlayCountStore.getInstance(context).getTopPlayedResults(NUMBER_OF_TOP_TRACKS);

        try {
            return makeSortedCursor(context, songs,
                    songs.getColumnIndex(SongPlayCountStore.SongPlayCountColumns.ID));
        } finally {
            if (songs != null) {
                songs.close();
            }
        }
    }

    @Nullable
    private static SortedLongCursor makeSortedCursor(@NonNull final Context context, @Nullable final Cursor cursor, final int idColumn) {
        if (cursor != null && cursor.moveToFirst()) {
            // create the list of ids to select against
            StringBuilder selection = new StringBuilder();
            selection.append(BaseColumns._ID);
            selection.append(" IN (");

            // this tracks the order of the ids
            long[] order = new long[cursor.getCount()];

            long id = cursor.getLong(idColumn);
            selection.append(id);
            order[cursor.getPosition()] = id;

            while (cursor.moveToNext()) {
                selection.append(",");

                id = cursor.getLong(idColumn);
                order[cursor.getPosition()] = id;
                selection.append(id);
            }

            selection.append(")");

            // get a list of songs with the data given the selection statement
            Cursor songCursor = SongLoader.makeSongCursor(context, selection.toString(), null);
            if (songCursor != null) {
                // now return the wrapped TopTracksCursor to handle sorting given order
                return new SortedLongCursor(songCursor, order, BaseColumns._ID);
            }
        }

        return null;
    }
}
