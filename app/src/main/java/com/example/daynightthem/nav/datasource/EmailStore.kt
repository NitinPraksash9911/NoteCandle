package com.example.daynightthem.nav.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object EmailStore {

    private val allEmails = mutableListOf(
        Email(
            0L,

            "Package shipped!",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        Email(
            1L,

            "Brunch this weekend?",
            """
                I'll be in your neighborhood doing errands and was hoping to catch you for a coffee this Saturday. If you don't have anything scheduled, it would be great to see you! It feels like its been forever.

                If we do get a chance to get together, remind me to tell you about Kim. She stopped over at the house to say hey to the kids and told me all about her trip to Mexico.

                Talk to you soon,

                Ali
            """.trimIndent()
        ),
        Email(
            2L,

            "Bonjour from Paris",
            "Here are some great shots from my trip...",

            true
        ),
        Email(
            3L,
            "High school reunion?",
            """
                Hi friends,

                I was at the grocery store on Sunday night.. when I ran into Genie Williams! I almost didn't recognize her afer 20 years!

                Anyway, it turns out she is on the organizing committee for the high school reunion this fall. I don't know if you were planning on going or not, but she could definitely use our help in trying to track down lots of missing alums. If you can make it, we're doing a little phone-tree party at her place next Saturday, hoping that if we can find one person, thee more will...
            """.trimIndent(),
        ),
        Email(
            4L,

            "Brazil trip",
            """
                Thought we might be able to go over some details about our upcoming vacation.

                I've been doing a bit of research and have come across a few paces in Northern Brazil that I think we should check out. One, the north has some of the most predictable wind on the planet. I'd love to get out on the ocean and kitesurf for a couple of days if we're going to be anywhere near or around Taiba. I hear it's beautiful there and if you're up for it, I'd love to go. Other than that, I haven't spent too much time looking into places along our road trip route. I'm assuming we can find places to stay and things to do as we drive and find places we think look interesting. But... I know you're more of a planner, so if you have ideas or places in mind, lets jot some ideas down!

                Maybe we can jump on the phone later today if you have a second.
            """.trimIndent(),
            isStarred = true
        ),
        Email(
            5L,
            "Update to Your Itinerary",
            ""
        ),
        Email(
            6L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            9L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            8L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            10L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            11L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ), Email(
            12L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ), Email(
            12L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            14L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ), Email(
            15L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            16L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ), Email(
            17L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            18L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ),
        Email(
            19L,

            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
        ), Email(
            21L,

            "New Maiolasdasay",
            "Back to position mansdsad " +
                    "fatatfst",
        )
    )

    private val _emails: MutableLiveData<List<Email>> = MutableLiveData()

    init {
        _emails.value = allEmails
    }

    val emails: LiveData<List<Email>>
        get() = _emails

    /**
     * Get an [Email] with the given [id].
     */
    fun get(id: Long): Email? {
        return allEmails.firstOrNull { it.id == id }
    }
}