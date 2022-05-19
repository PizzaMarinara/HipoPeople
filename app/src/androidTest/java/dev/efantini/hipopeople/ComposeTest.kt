package dev.efantini.hipopeople

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.efantini.hipopeople.domain.model.Hipo
import dev.efantini.hipopeople.domain.model.Member
import dev.efantini.hipopeople.presentation.memberslist.elements.ListWithSearch
import dev.efantini.hipopeople.presentation.memberslist.states.MembersListState
import dev.efantini.hipopeople.presentation.shared.theme.HipoPeopleTheme
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @ExperimentalMaterialApi
    @Test
    fun searchListTest() {

        val members = listOf(
            Member(
                "Enrico",
                30,
                "Italy",
                "PizzaMarinara",
                Hipo("Applicant", 0)
            )
        )

        composeTestRule.setContent {
            HipoPeopleTheme {
                ListWithSearch(
                    state = MembersListState(members = members),
                    onMemberClicked = { },
                    onAddMemberClicked = { }
                )
            }
        }

        val root = composeTestRule.onRoot(useUnmergedTree = false)
        // root.printToLog("ComposeLog: root")
        val searchBox = root.onChildAt(1)
        // searchBox.printToLog("ComposeLog: searchBox")

        // Check if it exists at first.
        composeTestRule.onNode(hasText("Enrico", ignoreCase = true)).assertExists()

        // Search should work with a substring
        searchBox.performTextInput("rico")
        composeTestRule.onNodeWithText("Enrico").assertExists()

        // Search should work with the position field inside the Hipo embedded class
        searchBox.performTextInput("Applicant")
        composeTestRule.onNodeWithText("Enrico").assertExists()

        // Search should work and hide if nothing matches
        searchBox.performTextInput("Taylan")
        composeTestRule.onNodeWithText("Enrico").assertDoesNotExist()
    }
}
