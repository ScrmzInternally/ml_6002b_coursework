package tsml.classifiers;

import com.sun.istack.internal.NotNull;
import weka.classifiers.Classifier;
import weka.core.Instances;

public interface ProgressiveBuildClassifier
    extends Classifier {

    // make sure to start / stop timers / watchers at the beginning / end of each of these methods as they can be
    // called from anywhere! I.e. someone might call hasNextBuildTick(), wait 1 min, then call nextBuildTick(). You
    // need to stop timers in that time otherwise you're timings are out.

    default boolean hasNextBuildTick() throws Exception {
        return false;
    }

    default void nextBuildTick() throws Exception {

    }

    default void finishBuild() throws Exception {

    }

    default void startBuild(@NotNull Instances data) throws
                                                     Exception {}

    @Override
    default void buildClassifier(@NotNull Instances trainData) throws Exception {
        startBuild(trainData);
        if (hasNextBuildTick()) {
            do {
                nextBuildTick();
            }
            while (hasNextBuildTick());
            finishBuild();
        }
    }
}
