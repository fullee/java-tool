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

public class Gh103 extends TestBase {

    @Test
    public void test() {
        S.Buffer buf = S.buffer();
        buf.write("abc");
        eq("abc", buf.toString());

        buf = S.buffer();
        buf.write(new char[]{'a', 'b', 'c'});
        eq("abc", buf.toString());

        buf = S.buffer();
        buf.write(66);
        eq("B", buf.toString());

        buf = S.buffer();
        buf.write("abcdef", 2, 3);
        eq("cde", buf.toString());

    }

}
