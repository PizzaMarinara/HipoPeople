package dev.efantini.hipopeople.presentation.addmember.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun HipoForm(
    modifier: Modifier = Modifier,
    state: HipoFormState,
    fields: List<HipoFormField>
) {
    state.fields = fields

    LazyColumn(modifier = modifier) {
        items(fields) {
            it.Content()
        }
    }
}

class HipoFormField(
    val name: String,
    private val label: String = "",
    private val hint: String = "",
    private val keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    var text: String by mutableStateOf("")

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        TextField(
            value = text,
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = hint,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            label = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            keyboardOptions = keyboardOptions,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(0.9F),
            onValueChange = { value ->
                text = value
            }
        )
    }
}

class HipoFormState {
    var fields: List<HipoFormField> = listOf()
    fun getData(): Map<String, String> = fields.associate { it.name to it.text }
}
