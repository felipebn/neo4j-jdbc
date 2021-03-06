/*
 * Copyright (c) 2016 LARUS Business Automation [http://www.larus-ba.it]
 * <p>
 * This file is part of the "LARUS Integration Framework for Neo4j".
 * <p>
 * The "LARUS Integration Framework for Neo4j" is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created on 09/03/16
 */
package org.neo4j.jdbc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author AgileLARUS
 * @since 3.0.0
 */
public class ConnectionTest {

	@Rule public ExpectedException expectedEx = ExpectedException.none();

	/*------------------------------*/
	/*         isWrapperFor         */
	/*------------------------------*/

	@Test public void isWrapperForShouldReturnTrue() throws SQLException {
		Connection connection = mock(Connection.class, Mockito.CALLS_REAL_METHODS);

		assertTrue(connection.isWrapperFor(Connection.class));
		assertTrue(connection.isWrapperFor(java.sql.Connection.class));
		assertTrue(connection.isWrapperFor(java.sql.Wrapper.class));
		assertTrue(connection.isWrapperFor(java.lang.AutoCloseable.class));
	}

	@Test public void isWrapperForShouldReturnFalse() throws SQLException {
		Connection connection = mock(Connection.class, Mockito.CALLS_REAL_METHODS);

		assertFalse(connection.isWrapperFor(Statement.class));
		assertFalse(connection.isWrapperFor(java.sql.Driver.class));
		assertFalse(connection.isWrapperFor(ResultSet.class));
		assertFalse(connection.isWrapperFor(java.sql.ResultSet.class));
	}

	/*------------------------------*/
	/*            unwrap            */
	/*------------------------------*/

	@Test public void unwrapShouldReturnCorrectClass() throws SQLException {
		Connection connection = mock(Connection.class, Mockito.CALLS_REAL_METHODS);

		assertNotNull(connection.unwrap(Connection.class));
		assertNotNull(connection.unwrap(java.sql.Connection.class));
		assertNotNull(connection.unwrap(java.sql.Wrapper.class));
		assertNotNull(connection.unwrap(java.lang.AutoCloseable.class));
	}

	@Test public void unwrapShouldThrowException() throws SQLException {
		expectedEx.expect(SQLException.class);

		Connection connection = mock(Connection.class, Mockito.CALLS_REAL_METHODS);

		connection.unwrap(Statement.class);
	}
}
