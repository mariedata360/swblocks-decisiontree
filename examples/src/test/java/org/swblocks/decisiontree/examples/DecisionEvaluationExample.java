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

package org.swblocks.decisiontree.examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swblocks.decisiontree.BuilderLoader;
import org.swblocks.decisiontree.DecisionTree;
import org.swblocks.decisiontree.Input;
import org.swblocks.decisiontree.OutputResults;
import org.swblocks.decisiontree.domain.DecisionTreeRuleSet;
import org.swblocks.decisiontree.domain.builders.RuleBuilder;
import org.swblocks.decisiontree.domain.builders.RuleSetBuilder;
import org.swblocks.decisiontree.tree.DecisionTreeType;
import org.swblocks.jbl.builders.Builder;

public class DecisionEvaluationExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionEvaluationExample.class);
    private Builder<RuleSetBuilder, DecisionTreeRuleSet> builder;

    @Before
    public void setUp() {
        builder = RuleSetBuilder.creator("commissions",
                Arrays.asList("Test", "Company", "Group", "Team"));

        builder.with(RuleSetBuilder::rule, RuleBuilder.creator()
                .with(RuleBuilder::input, Arrays.asList("UTC", "*", "*", "*"))
                .with(RuleBuilder::output, Collections.singletonMap("Coverage", "IR:0|70")));

        builder.with(RuleSetBuilder::rule, RuleBuilder.creator()
                .with(RuleBuilder::input, Arrays.asList("Sonar", "*", "*", "*"))
                .with(RuleBuilder::output, Collections.singletonMap("Critical", "0")));
    }

    @Test
    public void createTreeAndEvaluateACMEVideoGames() {
        /**
         * Create a decision tree which is not time aware {@link DecisionTreeType#SINGLE}.
         * Evaluate conditions for outputs.
         */
        final DecisionTree decisionTree = DecisionTree.instanceOf(BuilderLoader.instanceOf(builder),
                DecisionTreeType.SINGLE);

        final Input input = decisionTree.createInputs("UTC", "ACME", "Games", "Video");
        final Optional<OutputResults> evaluationFor = decisionTree.getSingleEvaluationFor(input);
        logResult(input, evaluationFor);

    }

    private void logResult(final Input input, final Optional<OutputResults> evaluationFor) {
        if (evaluationFor.isPresent()) {
            LOGGER.info("Found evaluation for {} in tree, the result is {}", input, evaluationFor.get().results());
        } else {
            LOGGER.info("No evaluation for {} in tree", input);
        }
    }
}
