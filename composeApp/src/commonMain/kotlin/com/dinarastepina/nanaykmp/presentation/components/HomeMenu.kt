package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dinarastepina.nanaykmp.presentation.ui.green10
import com.dinarastepina.nanaykmp.presentation.ui.green70
import com.dinarastepina.nanaykmp.presentation.ui.purple10
import com.dinarastepina.nanaykmp.presentation.ui.purple70
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.dictionary
import nanaykmp.composeapp.generated.resources.gummy_notebook
import nanaykmp.composeapp.generated.resources.phrasebook
import nanaykmp.composeapp.generated.resources.question_and_answer_svgrepo_com
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeMenu(
    onDictionaryClick: () -> Unit,
    onPhrasebookClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionCard(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.dictionary),
            drawableResource = Res.drawable.gummy_notebook,
            borderColor = purple70,
            backgroundColor = purple10
        )
        SectionCard(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.phrasebook),
            drawableResource = Res.drawable.question_and_answer_svgrepo_com,
            borderColor = green70,
            backgroundColor = green10
        )
    }
}