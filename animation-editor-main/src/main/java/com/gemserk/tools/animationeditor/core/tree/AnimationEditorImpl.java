package com.gemserk.tools.animationeditor.core.tree;

import com.gemserk.tools.animationeditor.core.SkeletonAnimation;
import com.gemserk.tools.animationeditor.core.SkeletonAnimationKeyFrame;
import com.gemserk.tools.animationeditor.core.AnimationUtils;
import com.gemserk.tools.animationeditor.core.JointUtils;
import com.gemserk.tools.animationeditor.core.Skeleton;

public class AnimationEditorImpl implements AnimationEditor {

	protected SkeletonEditor skeletonEditor;

	int index = 0;
	SkeletonAnimation currentAnimation = new SkeletonAnimation();
	SkeletonAnimationKeyFrame selectedKeyFrame;

	float duration = 0f;

	public AnimationEditorImpl(SkeletonEditor skeletonEditor) {
		this.skeletonEditor = skeletonEditor;
	}

	@Override
	public SkeletonAnimationKeyFrame addKeyFrame() {
		String name = "keyFrame" + index++;
		SkeletonAnimationKeyFrame keyFrame = AnimationUtils.keyFrame(name, JointUtils.cloneSkeleton(skeletonEditor.getSkeleton()), duration);
		currentAnimation.getKeyFrames().add(keyFrame);
		duration += 1f;

		// copy current skeleton values to keyframe.

		// skeletonEditor.setCurrentKeyFrame(keyFrame);

		return keyFrame;
	}

	@Override
	public void updateKeyFrame() {
		if (selectedKeyFrame == null)
			return;
		// throw new IllegalStateException("cant store current skeleton to no keyframe");
		// then we should disable the button when you can't save a keyframe!!
		AnimationUtils.updateKeyFrame(skeletonEditor.getSkeleton(), selectedKeyFrame);
	}

	@Override
	public void selectKeyFrame(SkeletonAnimationKeyFrame keyFrame) {
		selectedKeyFrame = keyFrame;
		Skeleton skeleton = skeletonEditor.getSkeleton();
		AnimationUtils.setKeyframeToSkeleton(skeleton, keyFrame);
	}

	@Override
	public void removeKeyFrame() {
		if (selectedKeyFrame != null)
			currentAnimation.getKeyFrames().remove(selectedKeyFrame);
	}

	@Override
	public SkeletonAnimation getCurrentAnimation() {
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
