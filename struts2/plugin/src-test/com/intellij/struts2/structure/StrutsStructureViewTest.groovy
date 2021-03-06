/*
 * Copyright 2013 The authors
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.struts2.structure

import com.intellij.ide.structureView.newStructureView.StructureViewComponent
import com.intellij.struts2.BasicLightHighlightingTestCase
import com.intellij.testFramework.PlatformTestUtil
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import com.intellij.util.Consumer
import com.intellij.util.ui.tree.TreeUtil

/**
 * @author Yann C&eacute;bron
 */
class StrutsStructureViewTest extends LightCodeInsightFixtureTestCase {

  @Override
  protected String getBasePath() {
    return BasicLightHighlightingTestCase.TEST_DATA_PATH + "/structure"
  }

  public void testDefaultPresentation() {
    myFixture.configureByFile("struts-structure.xml")
    myFixture.testStructureView(new Consumer<StructureViewComponent>() {
      @Override
      public void consume(StructureViewComponent component) {
        component.setActionActive(StructureViewTreeModel.HIDE_PARAMS_ID, false)
        TreeUtil.expandAll(component.getTree())

        PlatformTestUtil.assertTreeEqual(component.getTree(), """-struts-structure.xml
 myBean
 myConstant
 struts2.xml
 -myPackage
  -myAction
   success
   paramName
""")
      }
    })
  }

  public void testHideParam() {
    myFixture.configureByFile("struts-structure.xml")
    myFixture.testStructureView(new Consumer<StructureViewComponent>() {
      @Override
      public void consume(StructureViewComponent component) {
        component.setActionActive(StructureViewTreeModel.HIDE_PARAMS_ID, true)
        TreeUtil.expandAll(component.getTree())

        PlatformTestUtil.assertTreeEqual(component.getTree(), """-struts-structure.xml
 myBean
 myConstant
 struts2.xml
 -myPackage
  -myAction
   success
""")
      }
    })
  }
}