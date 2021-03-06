/*
 * This file is part of the swblocks-decisiontree library.
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

package org.swblocks.decisiontree;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.swblocks.jbl.eh.Result;

/**
 * Interface to support DecisionTree loading from external store.
 *
 * <p>The interface extends {@link Supplier} to provide the loading function and {@link Predicate} to identify if the
 * load could be retired in the event of a failure.
 *
 * @param <T> the object type
 */
public interface Loader<T> extends Supplier<Result<T>>, Predicate<Result<T>> {
}
