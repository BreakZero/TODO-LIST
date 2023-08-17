package com.easy.todolist.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.enum.TaskCategory
import com.easy.todolist.core.commom.getFormattedDateTime
import com.easy.todolist.model.Task

@Composable
fun TaskFormEditor(
    modifier: Modifier = Modifier,
    confirmButtonText: String,
    task: Task? = null,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    openDatePicker: () -> Unit,
    openImagePicker: () -> Unit,
    onConfirmed: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = task?.title.orEmpty(),
            onValueChange = onTitleChanged,
            placeholder = {
                Text(text = "Title")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 88.dp),
            value = task?.description.orEmpty(),
            onValueChange = onDescriptionChanged,
            placeholder = {
                Text(text = "Description")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = task?.deadline.getFormattedDateTime(),
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = "Deadline (Optional)")
            },
            trailingIcon = {
                IconButton(onClick = openDatePicker) {
                    Icon(imageVector = Icons.Default.EditCalendar, contentDescription = null)
                }
            }
        )

        task?.let {
            Spacer(modifier = Modifier.height(8.dp))
            AttachmentPhoto(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clickable {
                        openImagePicker()
                    },
                task = it
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onConfirmed
        ) {
            Text(text = confirmButtonText)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TaskFormEditor_Preview() {
    val timestamp = System.currentTimeMillis()
    TaskFormEditor(
        modifier = Modifier.fillMaxWidth(),
        task = Task(
            id = 0,
            title = "Title",
            description = "descriptionssssssss",
            deadline = timestamp,
            accentColor = TaskCategory.WORK.color,
            createAt = timestamp,
            attachment = null
        ),
        confirmButtonText = "Confirm",
        onTitleChanged = {},
        onDescriptionChanged = {},
        openDatePicker = { /*TODO*/ },
        openImagePicker = { /*TODO*/ }) {

    }
}