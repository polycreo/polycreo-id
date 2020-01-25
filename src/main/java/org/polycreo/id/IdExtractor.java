/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.polycreo.id;

import java.io.Serializable;
import java.util.function.Function;

/**
 * TODO miyamoto.daisuke.
 */
public interface IdExtractor extends Function<Object, Serializable> {
	
	/**
	 * Extract ID from given entity.
	 *
	 * @param entity a target entity from which this extract the ID
	 * @return extracted ID
	 * @throws IllegalArgumentException if the ID field is not found in the entity
	 */
	Serializable getId(Object entity);
	
	@Override
	default Serializable apply(Object o) {
		return getId(o);
	}
}
