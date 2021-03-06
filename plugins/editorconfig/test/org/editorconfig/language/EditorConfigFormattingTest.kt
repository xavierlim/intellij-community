// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.editorconfig.language

import com.intellij.application.options.CodeStyle
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

class EditorConfigFormattingTest : LightPlatformCodeInsightFixtureTestCase() {
  override fun getTestDataPath() = "${PathManager.getCommunityHomePath()}/plugins/editorconfig/testSrc/org/editorconfig/language/formatting"

  private fun doTestWithSettings(settings: CommonCodeStyleSettings.() -> Unit) {
    val file = myFixture.configureByFile("${getTestName(true)}/before/.editorconfig")

    val languageSettings = CodeStyle.getLanguageSettings(file, EditorConfigLanguage)
    languageSettings.settings()

    WriteCommandAction.runWriteCommandAction(project) {
      CodeStyleManager.getInstance(project).reformat(file)
    }

    myFixture.checkResultByFile("${getTestName(true)}/after/.editorconfig")
  }

  fun testAlignOnValues() = doTestWithSettings { ALIGN_GROUP_FIELD_DECLARATIONS = true }

  fun testNoAlignment() = doTestWithSettings { }
}
