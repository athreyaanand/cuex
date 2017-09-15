package xyz.tracestudios.currencyconverter;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.View;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;
import es.dmoral.prefs.Prefs;


public class Introactivity extends MaterialIntroActivity
{

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableLastSlideAlphaExitTransition(true);

        getNextButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.white)
                        .buttonsColor(R.color.blue)
                        .image(R.drawable.intro_first_screeen)
                        .title("Live Rates")
                        .description("Utilize the latest rates that update every hour!")
                        .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.blue)
                .image(R.drawable.intro_usd_source)
                .title("Source Market Rate Currency : USD")
                .description("All currency rates converted from US Dollar")
                .build());


        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.white)
                        .buttonsColor(R.color.blue)
                         .image(R.drawable.intro_second_screen)
                        .title("Simple Calculations")
                        .description("Convert any currency with one simple click!")
                        .build());


        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.blue)
                .image(R.drawable.intro_tick_mark)
                .title("That's It!")
                .description("Enjoy this ultimate currency exchange tool")
                .build());

    }


    @Override
    public void onFinish() {
        super.onFinish();
        Prefs.with(Introactivity.this).writeInt("intro_key", 01);
    }
}
