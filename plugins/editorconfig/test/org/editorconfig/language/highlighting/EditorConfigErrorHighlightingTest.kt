// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.editorconfig.language.highlighting

import com.intellij.openapi.application.PathManager
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

class EditorConfigErrorHighlightingTest : LightPlatformCodeInsightFixtureTestCase() {
  override fun getTestDataPath() =
    "${PathManager.getCommunityHomePath()}/plugins/editorconfig/testSrc/org/editorconfig/language/highlighting/error/"

  fun testDanglingDot() = doTest()
  fun testDanglingKey() = doTest()
  fun testInnerDots() = doTest()
  fun testSuspiciousLineBreak() = doTest()

  private fun doTest() {
    myFixture.testHighlighting(true, false, true, "${getTestName(true)}/.editorconfig")
  }
}
