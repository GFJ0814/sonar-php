/*
 * SonarQube PHP Plugin
 * Copyright (C) 2010 SonarSource and Akram Ben Aissi
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.php.checks;

import org.junit.Rule;
import org.junit.Test;
import org.sonar.php.PHPAstScanner;
import org.sonar.plugins.php.CheckTest;
import org.sonar.plugins.php.TestUtils;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifierRule;

public class DuplicateBranchImplementationCheckTest extends CheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void test() throws Exception {
    SourceFile file = PHPAstScanner.scanSingleFile(
      TestUtils.getCheckFile("DuplicateBranchImplementationCheck.php"), new DuplicateBranchImplementationCheck());

    checkMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(6).withMessage("Either merge this branch with the identical one on line 2 or change one of the implementations.")
      .next().atLine(8).withMessage("Either merge this branch with the identical one on line 2 or change one of the implementations.")
      .next().atLine(16).withMessage("Either merge this branch with the identical one on line 14 or change one of the implementations.")
      .next().atLine(27).withMessage("Either merge this case with the identical one on line 21 or change one of the implementations.");
  }
}
