package com.mosangeet.activities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mosangeet.R;
import com.mosangeet.activities.queue.QueueItemCallback;
import com.mosangeet.adapter.QueueAdapter;
import com.mosangeet.model.Music;
import com.mosangeet.player.PlayerQueue;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class QueueDialog extends BottomSheetDialog {

    public QueueDialog(@NonNull Context context, PlayerQueue queue) {
        super(context);
        setContentView(R.layout.dialog_queue);

        List<Music> musicList = new ArrayList<>(queue.getCurrentQueue());

        RecyclerView queueLayout = findViewById(R.id.queue_layout);
        assert queueLayout != null;

        queueLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        QueueAdapter queueAdapter = new QueueAdapter(context, musicList, queue);
        ItemTouchHelper.Callback callback = new QueueItemCallback(queueAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(queueLayout);

        queueLayout.setAdapter(queueAdapter);
        queueLayout.scrollToPosition(queue.getCurrentPosition());
    }
}
