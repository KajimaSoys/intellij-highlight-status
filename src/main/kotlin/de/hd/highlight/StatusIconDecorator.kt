package de.hd.highlight

import com.intellij.icons.AllIcons
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ProjectViewNodeDecorator
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.ui.JBColor
import com.intellij.ui.SimpleTextAttributes
import java.awt.Color
import javax.swing.Icon

class StatusIconDecorator : ProjectViewNodeDecorator {

    override fun decorate(node: ProjectViewNode<*>, data: PresentationData) {
        val project: Project = node.project
        val virtualFile: VirtualFile = node.virtualFile ?: return

        val psiFile = PsiManager.getInstance(project).findFile(virtualFile) ?: return
        val text = psiFile.text

        if (text.contains("// EXCLUDE FROM STATUS")) {
            return
        }

        val originalPresentation = data.presentableText

        when {
            text.contains("// STATUS: DONE") -> {
                if (text.contains("// ICON")) {
                    data.setIcon(DoneIcon)
                }
                data.clearText()
                data.addText(originalPresentation, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                data.addText(
                    " [DONE]",
                    SimpleTextAttributes(
                        SimpleTextAttributes.STYLE_BOLD,
                        JBColor(Color(0x389e34), Color(0x499c54))
                    )
                )
            }

            text.contains("// STATUS: WIP") -> {
                if (text.contains("// ICON")) {
                    data.setIcon(WipIcon)
                }
                data.clearText()
                data.addText(originalPresentation, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                data.addText(
                    " [IN PROGRESS]",
                    SimpleTextAttributes(
                        SimpleTextAttributes.STYLE_BOLD,
                        JBColor(Color(0xd98a00), Color(0xe0a431))
                    )
                )
            }

            text.contains("// STATUS: TEST") -> {
                if (text.contains("// ICON")) {
                    data.setIcon(TestIcon)
                }
                data.clearText()
                data.addText(originalPresentation, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                data.addText(
                    " [TEST]",
                    SimpleTextAttributes(
                        SimpleTextAttributes.STYLE_BOLD,
                        JBColor(Color(0x8e24aa), Color(0xb96ddb))
                    )
                )
            }

            text.contains("// STATUS: BUG") -> {
                if (text.contains("// ICON")) {
                    data.setIcon(BugIcon)
                }
                data.clearText()
                data.addText(originalPresentation, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                data.addText(
                    " [BUG]",
                    SimpleTextAttributes(
                        SimpleTextAttributes.STYLE_BOLD,
                        JBColor(Color(0xd32f2f), Color(0xef5350))
                    )
                )
            }

            text.contains("// STATUS: TODO") -> {
                if (text.contains("// ICON")) {
                    data.setIcon(TodoIcon)
                }
                data.clearText()
                data.addText(originalPresentation, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                data.addText(
                    " [TODO]",
                    SimpleTextAttributes(
                        SimpleTextAttributes.STYLE_PLAIN,
                        JBColor(Color(0x3d5afe), Color(0x539dfc))
                    )
                )
            }

            text.contains("// STATUS: INACTIVE") -> {
                if (text.contains("// ICON")) {
                    data.setIcon(InactiveIcon)
                }
                data.clearText()
                data.addText(originalPresentation, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                data.addText(" [INACTIVE]", SimpleTextAttributes.GRAYED_ATTRIBUTES)
            }
        }
    }

    object DoneIcon : Icon by AllIcons.General.GreenCheckmark
    object WipIcon : Icon by AllIcons.Process.Step_1
    object TestIcon : Icon by AllIcons.General.InspectionsOK
    object BugIcon : Icon by AllIcons.General.Error
    object TodoIcon : Icon by AllIcons.General.TodoDefault
    object InactiveIcon : Icon by AllIcons.General.Error
}
