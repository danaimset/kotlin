/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
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

package org.jetbrains.kotlin.integration;

import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.utils.StringsKt;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class CompilerSmokeTestBase extends KotlinIntegrationTestBase {
    @NotNull
    protected String getTestDataDir() {
        return KtTestUtil.getTestDataPathBase() + "/integration/smoke/" + getTestName(true);
    }

    protected int run(String logName, String... args) throws Exception {
        return runJava(getTestDataDir(), logName, args);
    }

    protected int runCompiler(String logName, String... arguments) throws Exception {
        Collection<String> javaArgs = new ArrayList<>();

        javaArgs.add("-cp");
        javaArgs.add(StringsKt.join(Arrays.asList(
                getCompilerLib().getAbsolutePath() + File.separator + "kotlin-compiler.jar"
        ), File.pathSeparator));
        javaArgs.add("org.jetbrains.kotlin.cli.jvm.K2JVMCompiler");

        Collections.addAll(javaArgs, arguments);

        return run(logName, ArrayUtil.toStringArray(javaArgs));
    }
}
