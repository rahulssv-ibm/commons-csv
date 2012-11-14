/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.csv;

import static org.apache.commons.csv.Constants.*;
import static org.junit.Assert.*;

import org.apache.commons.csv.CSVFormat.CSVFormatBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @version $Id$
 */
public class CSVFormatBuilderTest {

    private CSVFormatBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new CSVFormatBuilder('+', '!', null, '#', '!', true, true, CRLF, null);
    }

    @Test
    public void testDelimiter() {
        assertEquals('?', builder.withDelimiter('?').build().getDelimiter());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNewFormatLFThrowsException() {
        CSVFormat.newFormat(LF);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewFormatCRThrowsException() {
        CSVFormat.newFormat(CR);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWithDelimiterLFThrowsException() {
        builder.withDelimiter(LF).build();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testDelimiterSameAsEscapeThrowsException() {
        builder.withDelimiter('!').withEscape('!').build();
    }

    @Test(expected = IllegalStateException.class)
    public void testDelimiterSameAsCommentStartThrowsException() {
        builder.withDelimiter('!').withCommentStart('!').build();
    }

    @Test
    public void testQuoteChar() {
        assertEquals('?', builder.withQuoteChar('?').build().getQuoteChar().charValue());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testQuoteCharSameAsCommentStartThrowsException() {
        builder.withQuoteChar('!').withCommentStart('!').build();
    }

    @Test(expected = IllegalStateException.class)
    public void testQuoteCharSameAsCommentStartThrowsExceptionForWrapperType() {
        // Cannot assume that callers won't use different Character objects
        builder.withQuoteChar(new Character('!')).withCommentStart('!').build();
    }

    @Test(expected = IllegalStateException.class)
    public void testQuoteCharSameAsDelimiterThrowsException() {
        builder.withQuoteChar('!').withDelimiter('!').build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithQuoteLFThrowsException() {
        builder.withQuoteChar(LF).build();
    }

    @Test
    public void testQuotePolicy() {
        assertEquals(Quote.ALL, builder.withQuotePolicy(Quote.ALL).build().getQuotePolicy());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testQuotePolicyNoneWithoutEscapeThrowsException() {
        CSVFormat.newFormat('!').withQuotePolicy(Quote.NONE).build();
    }

    @Test
    public void testCommentStart() {
        assertEquals('?', builder.withCommentStart('?').build().getCommentStart().charValue());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWithCommentStartCRThrowsException() {
        builder.withCommentStart(CR).build();
    }

    @Test
    public void testRecoardSeparator() {
        assertEquals("?", builder.withRecordSeparator("?").build().getRecordSeparator());
    }
    
    @Test
    public void testEscape() {
        assertEquals('?', builder.withEscape('?').build().getEscape().charValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEscapeCRThrowsExceptions() {
        builder.withEscape(CR).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testEscapeSameAsCommentStartThrowsException() {
        builder.withEscape('!').withCommentStart('!').build();
    }

    @Test(expected = IllegalStateException.class)
    public void testEscapeSameAsCommentStartThrowsExceptionForWrapperType() {
        // Cannot assume that callers won't use different Character objects
        builder.withEscape(new Character('!')).withCommentStart(new Character('!')).build();
    }

    @Test
    public void testIgnoreSurroundingSpaces() {
        assertFalse(builder.withIgnoreSurroundingSpaces(false).build().getIgnoreSurroundingSpaces());
    }
    
    @Test
    public void testIgnoreEmptyLines() {
        assertFalse(builder.withIgnoreEmptyLines(false).build().getIgnoreEmptyLines());
    }
}
