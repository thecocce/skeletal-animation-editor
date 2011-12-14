package com.gemserk.tools.animationeditor.core.tree;

import com.gemserk.tools.animationeditor.core.Animation;
import com.gemserk.tools.animationeditor.core.AnimationKeyFrame;
import com.gemserk.tools.animationeditor.core.JointUtils;
import com.gemserk.tools.animationeditor.core.Skeleton;

public class AnimationEditorImpl implements AnimationEditor {

	protected SkeletonEditor skeletonEditor;

	int index = 0;
	Animation currentAnimation = new Animation();
	AnimationKeyFrame selectedKeyFrame;

	float duration = 0f;

	public AnimationEditorImpl(SkeletonEditor skeletonEditor) {
		this.skeletonEditor = skeletonEditor;
	}

	@Override
	public AnimationKeyFrame addKeyFrame() {
		String name = "keyFrame" + index++;
		AnimationKeyFrame keyFrame = new AnimationKeyFrame(name, new Skeleton(JointUtils.cloneTree(skeletonEditor.getRoot())), duration);
		currentAnimation.getKeyFrames().add(keyFrame);
		duration += 1f;

		skeletonEditor.setCurrentKeyFrame(keyFrame);

		return keyFrame;
	}

	@Override
	public void selectKeyFrame(AnimationKeyFrame keyFrame) {
		selectedKeyFrame = keyFrame;
		skeletonEditor.setCurrentKeyFrame(keyFrame);
		skeletonEditor.select(keyFrame.getSkeleton().getRoot());
	}

	@Override
	public void removeKeyFrame() {
		if (selectedKeyFrame != null)
			currentAnimation.getKeyFrames().remove(selectedKeyFrame);
	}

	@Override
	public Animation getCurrentAnimation() {
		return currentAnimation;
	}

	boolean playing = false;

	@Override
	public boolean isPlayingAnimation() {
		return playing;
	}

	@Override
	public void playAnimation() {
		playing = true;
	}

	@Override
	public void stopAnimation() {
		playing = false;
	}

}