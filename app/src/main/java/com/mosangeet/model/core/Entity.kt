package com.mosangeet.model.core

interface Entity: Searchable {
    /**
     * Unique identifier
     */
    val uid: String

    /**
     * Name that displays on user interface
     */
    val displayName: String
}