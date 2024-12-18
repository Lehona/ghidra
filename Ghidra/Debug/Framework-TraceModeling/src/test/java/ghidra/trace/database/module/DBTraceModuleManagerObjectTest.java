/* ###
 * IP: GHIDRA
 *
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
 */
package ghidra.trace.database.module;

import org.junit.*;

import db.Transaction;
import ghidra.trace.model.target.schema.SchemaContext;
import ghidra.trace.model.target.schema.XmlSchemaContext;
import ghidra.trace.model.target.schema.TraceObjectSchema.SchemaName;

public class DBTraceModuleManagerObjectTest extends DBTraceModuleManagerTest {

	protected SchemaContext ctx;

	@Before
	public void setUpObjectsMode() throws Exception {
		ctx = XmlSchemaContext.deserialize("" + //
			"<context>" + //
			"    <schema name='Session' elementResync='NEVER' attributeResync='ONCE'>" + //
			"        <attribute name='Modules' schema='ModuleContainer' />" + //
			"    </schema>" + //
			"    <schema name='ModuleContainer' canonical='yes' elementResync='NEVER' " + //
			"            attributeResync='ONCE'>" + //
			"        <element schema='Module' />" + //
			"    </schema>" + //
			"    <schema name='Module' elementResync='NEVER' attributeResync='NEVER'>" + //
			"        <interface name='Module' />" + //
			"        <attribute name='Sections' schema='SectionContainer' />" + //
			"    </schema>" + //
			"    <schema name='SectionContainer' canonical='yes' elementResync='NEVER' " + //
			"            attributeResync='ONCE'>" + //
			"        <element schema='Section' />" + //
			"    </schema>" + //
			"    <schema name='Section' elementResync='NEVER' attributeResync='NEVER'>" + //
			"        <interface name='Section' />" + //
			"    </schema>" + //
			"</context>");

		try (Transaction tx = b.startTransaction()) {
			b.trace.getObjectManager().createRootObject(ctx.getSchema(new SchemaName("Session")));
		}
	}

	@Test
	@Override
	@Ignore // Undo not supported with object-manager's write-back cache
	public void testUndoIdentitiesPreserved() throws Exception {
		super.testUndoIdentitiesPreserved();
	}

	@Test
	@Override
	@Ignore // Undo not supported with object-manager's write-back cache
	public void testUndoThenRedo() throws Exception {
		super.testUndoThenRedo();
	}
}
