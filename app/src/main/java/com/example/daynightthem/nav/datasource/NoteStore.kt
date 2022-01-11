package com.example.daynightthem.nav.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.daynightthem.nav.datasource.model.NoteInfo
import com.example.daynightthem.nav.datasource.model.NoteInfo.Calendar
import com.example.daynightthem.nav.datasource.model.NoteInfo.Note
import com.example.daynightthem.nav.datasource.model.NoteInfo.Reminder
import com.example.daynightthem.nav.datasource.model.NoteInfo.TodoData

object NoteStore {

    private val allNotes = mutableListOf(
        Calendar(
            1L,

            "Calendar",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        Calendar(
            2L,

            "Calendar",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        Calendar(
            3L,

            "Calendar",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        Note(
            4L,

            subject = "Note",
            body = """
                                Cucumber Mask Facial has shipped.
                
                                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.
                
                                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
                            """.trimIndent(),
            isStarred = true
        ),
        Note(
            5L,

            "Note",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        TodoData(
            6L,

            "Todo",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        TodoData(
            7L,

            "Todo",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        Reminder(
            8L,

            "Reminder",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        )
    )

    private var _notes: MutableLiveData<List<NoteInfo>> = MutableLiveData()

    init {
        _notes.value = allNotes
    }

    private val calendar: LiveData<List<NoteInfo>>
        get() = Transformations.map(notes) { notesInfo ->
            notesInfo.filterIsInstance(Calendar::class.java)
        }

    private val notesList: LiveData<List<NoteInfo>>
        get() = Transformations.map(notes) { notesInfo ->
            notesInfo.filterIsInstance(Note::class.java)
        }
    private val reminders: LiveData<List<NoteInfo>>
        get() = Transformations.map(notes) { notesInfo ->
            notesInfo.filterIsInstance(Reminder::class.java)
        }
    private val todos: LiveData<List<NoteInfo>>
        get() = Transformations.map(notes) { notesInfo ->
            notesInfo.filterIsInstance(TodoData::class.java)
        }

    inline fun <reified C : NoteInfo> getNoteFilteredList(): List<C> {

//        _notes.value = Transformations.map(_notes) { notesInfo ->
//            notesInfo.filterIsInstance(C::class.java)
//        }.value

        return notes.value!!.filterIsInstance<C>()
    }


    val notes: LiveData<List<NoteInfo>>
        get() = _notes

    /**
     * Get an [Email] with the given [id].
     */
    fun get(id: Long): NoteInfo? {
        return allNotes.firstOrNull { it.id == id }
    }
}