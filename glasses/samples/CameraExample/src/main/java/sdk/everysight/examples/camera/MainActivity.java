/*
 * This work contains files distributed in Android, such files Copyright (C) 2016 The Android Open Source Project
 *
 * and are Licensed under the Apache License, Version 2.0 (the "License"); you may not use these files except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
*/

package sdk.everysight.examples.camera;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.everysight.base.EvsBaseActivity;
import com.everysight.carousel.EvsCarouselActivity;
import com.everysight.common.carouselm.CarouselBehavior;
import com.everysight.common.carouselm.CarouselX;
import com.everysight.common.carouselm.ItemInfo;
import com.everysight.common.carouselm.OnCarouselItemClickListener;
import com.everysight.environment.EvsConfiguration;
import com.everysight.environment.EvsConsts;
import com.everysight.notifications.EvsToast;

import java.io.File;
import java.util.ArrayList;

/*
This is a standard Android camera example
*/
public class MainActivity extends EvsCarouselActivity
{
	private final String TAG = "CameraSample";
	private TextView mCxtCenterLable = null;
	private TextView mMenuLable = null;
	private ArrayList<ItemInfo> mMainMenu = null;
    private ArrayList<ItemInfo> mSecMenu = null;

    private CarouselBehavior mMainCarouselBehavior;
	private CarouselBehavior mMSecCarouselBehavior;

    public class secCarClass extends EvsBaseActivity {
            private CarouselX mCarousel;
            private ViewGroup mCarouselHolder;
            private LinearLayout mCarouselLayout;
            private boolean mReverseCarousel = false;
            private boolean mDownClosesMenu = false;

            public secCarClass() {
            }
            @Override
            protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
            }
            protected CarouselX getMenuCarousel() {
                return this.mCarousel;
            }

            protected void openMenuCarousel(boolean downClosesMenu, ArrayList<ItemInfo> items, OnCarouselItemClickListener onCarouselItemClickListener) {
                this.openMenuCarousel(downClosesMenu, items, (ViewGroup)null, onCarouselItemClickListener, (CarouselBehavior)null);
            }

            protected void openMenuCarousel(boolean downClosesMenu, ArrayList<ItemInfo> items, ViewGroup parentLayout, OnCarouselItemClickListener onCarouselItemClickListener, CarouselBehavior carouselBehavior) {
                this.openMenuCarousel(downClosesMenu, items, parentLayout, onCarouselItemClickListener, carouselBehavior, 0, false);
            }

            protected void openMenuCarousel(boolean downClosesMenu, ArrayList<ItemInfo> items, ViewGroup parentLayout, OnCarouselItemClickListener onCarouselItemClickListener, CarouselBehavior carouselBehavior, int centerItemPsoition) {
                this.openMenuCarousel(downClosesMenu, items, parentLayout, onCarouselItemClickListener, carouselBehavior, centerItemPsoition, false);
            }

            protected void openMenuCarousel(boolean downClosesMenu, ArrayList<ItemInfo> items, ViewGroup parentLayout, OnCarouselItemClickListener onCarouselItemClickListener, CarouselBehavior carouselBehavior, boolean enableLowerText) {
                this.openMenuCarousel(downClosesMenu, items, parentLayout, onCarouselItemClickListener, carouselBehavior, 0, enableLowerText);
            }

            protected void openMenuCarousel(boolean downClosesMenu, ArrayList<ItemInfo> items, ViewGroup parentLayout, OnCarouselItemClickListener onCarouselItemClickListener, CarouselBehavior carouselBehavior, int centerItemPsoition, boolean enableLowerText) {
                this.mDownClosesMenu = downClosesMenu;
                if(this.isCarouselOpened()) {
                    this.closeMenuCarousel();
                }

                this.mCarouselHolder = parentLayout;
                if(this.mCarouselHolder == null) {
                    this.mCarouselHolder = (ViewGroup)this.findViewById(2131230760);
                }

                this.mCarouselLayout = (LinearLayout)View.inflate(this, com.everysight.common.carouselm.R.layout.carouselx, (ViewGroup)null);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
                params.addRule(13, -1);
                this.mCarouselHolder.addView(this.mCarouselLayout, params);
                this.mCarousel = (CarouselX)this.mCarouselLayout.findViewById(com.everysight.common.carouselm.R.id.activity_main_carousel);
                this.mCarousel.setTouchBarWidth(300);
                this.mCarousel.enableUpperText(true);
                this.mCarousel.enableLowerText(enableLowerText);
                this.mCarousel.setOnCarouselItemClickListener(onCarouselItemClickListener);
                if(carouselBehavior != null) {
                    this.mCarousel.setBehavior(carouselBehavior);
                }

                this.mCarousel.setCarouselItemsInfo(items, centerItemPsoition);
            }

            public boolean isCarouselOpened() {
                return this.mCarousel != null;
            }

            protected void closeMenuCarousel() {
                if(this.mCarouselHolder != null) {
                    this.mCarouselHolder.removeView(this.mCarouselLayout);
                }

                this.mCarousel = null;
            }

            protected void onResume() {
                this.mReverseCarousel = EvsConfiguration.Default().reverseCarousel();
                super.onResume();
            }

            public void onDown() {
                if(this.isCarouselOpened() && this.mDownClosesMenu) {
                    this.closeMenuCarousel();
                    this.onDownCompleted();
                } else {
                    super.onDown();
                }

            }

            public void onTap() {
                if(this.isCarouselOpened()) {
                    if(this.mCarousel != null) {
                        this.mCarousel.clientRequestSelection();
                    }
                } else {
                    super.onTap();
                }

            }

            public void onForward() {
                if(this.mCarousel != null) {
                    if(this.mReverseCarousel) {
                        this.mCarousel.clientRequestLeapLeft();
                    } else {
                        this.mCarousel.clientRequestLeapRight();
                    }

                } else {
                    super.onForward();
                }
            }

            public void onBackward() {
                if(this.mCarousel != null) {
                    if(this.mReverseCarousel) {
                        this.mCarousel.clientRequestLeapRight();
                    } else {
                        this.mCarousel.clientRequestLeapLeft();
                    }
                }

            }
        }
        public secCarClass secCar;
	private CameraHandler mCamera;
	private boolean mIsCameraOpen = false;
	private AnimatorSet mAnim;
	private ImageView mImage;

    public MainActivity() {
    }

    /******************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        secCar = new secCarClass();
        //secCar.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);

		mCxtCenterLable = (TextView) this.findViewById(R.id.centerLabel);
		mMenuLable = (TextView) this.findViewById(R.id.menuLabel);
		//mImage = (ImageView) this.findViewById(R.id.imageView);
		mCamera = new CameraHandler(this);

		ObjectAnimator scanAnim = ObjectAnimator.ofFloat(mMenuLable, View.ALPHA, 0f).setDuration(1000);
		scanAnim.setRepeatMode(ObjectAnimator.REVERSE);
		scanAnim.setRepeatCount(ObjectAnimator.INFINITE);
		mAnim = new AnimatorSet();
		mAnim.play(scanAnim);


		initMainMenu();


	}

	private void initMainMenu()
	{
		mMainCarouselBehavior = new CarouselBehavior();
		mMSecCarouselBehavior = new CarouselBehavior();
		mMSecCarouselBehavior.setSelectionAnimation(CarouselBehavior.CarouselSelectionAnimationType.fullAnimation);
		mMSecCarouselBehavior.setOpeningAnimationType(CarouselBehavior.CarouselOpeningAnimationType.applicationMenu);
		mMainCarouselBehavior.setSelectionAnimation(CarouselBehavior.CarouselSelectionAnimationType.fullAnimation);
		mMainCarouselBehavior.setOpeningAnimationType(CarouselBehavior.CarouselOpeningAnimationType.applicationMenu);

		mMainMenu = new ArrayList<>();
        mSecMenu = new ArrayList<>();

		ItemInfo item = new ItemInfoExe("Back", getResources().getDrawable(R.drawable.icon_back))
		{
			@Override
			public void execute()
			{
			}
		};
		mMainMenu.add(item);
		Drawable d = Drawable.createFromPath(new File(EvsConsts.EVS_PICTURES_DIR, "PIC_" + "1493217735582" + ".jpg").getAbsolutePath());
		 item = new ItemInfoExe("Camera", d)//getResources().getDrawable(R.drawable.ic_app_camera))
		{
			@Override
			public void execute()
			{
				openCamera();
			}
		};
		mMainMenu.add(item);



		item = new ItemInfoExe("Video", getResources().getDrawable(R.drawable.ic_video_camera))
		{
			@Override
			public void execute()
			{
				openVideo();
			}
		};
        mSecMenu.add(item);
	}

	private void openCamera()
	{
		if(mIsCameraOpen)
		{
			EvsToast.show(this,"Camera is already active");
			return;
		}
		mMenuLable.setText("Taking a picture");
		if (!mAnim.isRunning())
		{
			mAnim.start();
		}
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				mIsCameraOpen = true;

				mCamera.takePicture(
						new File(EvsConsts.EVS_PICTURES_DIR, "PIC_" + System.currentTimeMillis() + ".jpg").getAbsolutePath(),
						new CameraHandler.ICameraCallback()
						{
							@Override
							public void pictureTakenEnded(final boolean isOK,final String path)
							{
								mIsCameraOpen = false;
								MainActivity.this.runOnUiThread(new Runnable()
								{
									@Override
									public void run()
									{
										mMenuLable.setText("Tap to open menu");
										if(isOK)
										{
											//mImage.setImageURI(Uri.fromFile(new File(path)));
										}
										if (mAnim.isRunning())
										{
											mAnim.end();
											mMenuLable.setAlpha(1);
										}
									}
								});
							}

							@Override
							public void videoTakenEnded(boolean isOK,final String path)
							{

							}
						});
			}
		}).start();
	}

	private void openVideo()
	{
		if(mIsCameraOpen)
		{
			EvsToast.show(this,"Camera is already active");
			return;
		}
		mMenuLable.setText("Recording a video");
		if (!mAnim.isRunning())
		{
			mAnim.start();
		}
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				mCamera.recordVideo(new File(EvsConsts.EVS_VIDEO_DIR, "MOV_" + System.currentTimeMillis() + ".mp4").getAbsolutePath(),
						new CameraHandler.ICameraCallback()
						{
							@Override
							public void pictureTakenEnded(boolean isOK,final String path)
							{

							}

							@Override
							public void videoTakenEnded(boolean isOK,final String path)
							{
								mIsCameraOpen = false;
								MainActivity.this.runOnUiThread(new Runnable()
								{
									@Override
									public void run()
									{
										mMenuLable.setText("Tap to open menu");
										if (mAnim.isRunning())
										{
											mAnim.end();
											mMenuLable.setAlpha(1);
										}
									}
								});
							}
						});
			}
		}).start();
	}


	/******************************************************************/
	@Override
	public void onDestroy()
	{
		// clean up once we're done
		super.onDestroy();

	}

	@Override
	protected void onDownCompleted()
	{
		super.onDownCompleted();
		if(!isCarouselOpened())
		{
			mMenuLable.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void onTap()
	{
		super.onTap();
		if(!isCarouselOpened())
		{
			openMainMenu();
			return;
		}

	}

	private void openMainMenu()
	{
		mMenuLable.setVisibility(View.GONE);

		openMenuCarousel(true, mMainMenu, (ViewGroup) findViewById(R.id.SelectSettingsCarousel), new OnCarouselItemClickListener()
		{
			@Override
			public void onItemClick(int i, ItemInfo itemInfo)
			{
				((ItemInfoExe)itemInfo).execute();
				closeMenuCarousel();
				mMenuLable.setVisibility(View.VISIBLE);
			}
		},mMainCarouselBehavior);
		mCxtCenterLable.setVisibility(View.GONE);
/*
        secCar.openMenuCarousel(true, mSecMenu, (ViewGroup) findViewById(R.id.SecCarousel), new OnCarouselItemClickListener()
        {
            @Override
            public void onItemClick(int i, ItemInfo itemInfo)
            {
                ((ItemInfoExe)itemInfo).execute();
                secCar.closeMenuCarousel();
                mMenuLable.setVisibility(View.VISIBLE);
            }
        },mMSecCarouselBehavior);*/
		mCxtCenterLable.setVisibility(View.VISIBLE);
	}
}
