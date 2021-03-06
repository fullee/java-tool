package org.osgl.issues;

/*-
 * #%L
 * Java Tool
 * %%
 * Copyright (C) 2014 - 2018 OSGL (Open Source General Library)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;
import org.osgl.TestBase;
import org.osgl.util.S;

public class Gh161 extends TestBase {

    @Test
    public void testUrlSafeRandom() {
        for (int i = 0; i < 10000; ++i) {
            yes(isUrlSafe(S.longUrlSafeRandom()));
        }
    }

    static final char[] CHARS = {'$', '#', '^', '&', '!', '@'};

    private boolean isUrlSafe(String s) {
        for (char c : CHARS) {
            if (s.indexOf((int)c) >= 0) {
                return false;
            }
        }
        return true;
    }

}
