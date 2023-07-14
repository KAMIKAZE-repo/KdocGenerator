package com.nova.kdocgenerator.util

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ScrollType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.codeStyle.CodeStyleManager

object CodeInsertionUtil {
    fun insertCode(project: Project, editor: Editor, generatedCode: String) {
        val document: Document = editor.document
        val offset: Int = editor.caretModel.offset

        val selectionModel = editor.selectionModel
        val endOffset = selectionModel.selectionEnd

        WriteCommandAction.runWriteCommandAction(project) {
            document.deleteString(offset, endOffset)
            document.insertString(offset, generatedCode)
            reformatCode(project, document, offset, generatedCode.length)
        }

        editor.caretModel.moveToOffset(offset + generatedCode.length)
        editor.scrollingModel.scrollToCaret(ScrollType.MAKE_VISIBLE)
    }

    private fun reformatCode(project: Project, document: Document, offset: Int, length: Int) {
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document) ?: return
        val codeStyleManager = CodeStyleManager.getInstance(project)
        codeStyleManager.reformatText(psiFile, offset, offset + length)
    }
}


