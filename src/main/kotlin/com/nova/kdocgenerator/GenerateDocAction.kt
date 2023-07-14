package com.nova.kdocgenerator

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.*
import com.intellij.openapi.ui.Messages
import com.nova.kdocgenerator.network.GPTNetworkCall
import com.nova.kdocgenerator.util.CodeInsertionUtil


class CodeGenAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        //get editor
        val editor: Editor? = event.getData(PlatformDataKeys.EDITOR)

        // Get the current project and selected text
        val project = event.project
        val selectedText = getSelectedText(event)

        // Show a dialog with the selected text
        if (project != null && selectedText != null) {
            try {
                val response = GPTNetworkCall.gptNetworkCall(selectedText)
                if (editor != null) {
                    CodeInsertionUtil.insertCode(project, editor, response)
                } else {
                    Messages.showMessageDialog(project, "response: $response", "generated Kdoc", Messages.getInformationIcon())
                }
            } catch (e: Exception) {
                print(e.stackTrace.toString())
                Messages.showMessageDialog(project, "response: ${e.message}", "generated Kdoc", Messages.getInformationIcon())
            }
        }
    }

    override fun update(event: AnActionEvent) {
        // Enable the action only when text is selected
        val selectedText = getSelectedText(event)
        event.presentation.isEnabledAndVisible = selectedText != null
    }

    private fun getSelectedText(event: AnActionEvent): String? {
        val editor: Editor? = event.getData(PlatformDataKeys.EDITOR)
        if (editor != null) {
            val selectionModel: SelectionModel = editor.selectionModel
            return selectionModel.selectedText
        }
        return null
    }
}
