package zeinhijazi.com.sentencegenerator;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class mainscreen extends AppCompatActivity {

    public final static String EXTRA_ARRAYLIST = "zeinhijazi.com.sentencegenerator.MESSAGE";

    private Sentence sentence;
    private TextView textView;
    private TextView generateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        sentence = new Sentence(this);
        textView = (TextView)findViewById(R.id.randSentenceTV);
        generateText = (TextView)findViewById(R.id.genTextView);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Yellowtail-Regular.ttf");
        generateText.setTypeface(typeface);

        textView.setText("Click generate to generate a random sentence!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_history:
                openHistory();
                // open the history activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void genRandomSentence(View view) {
        sentence.constructRandomSentence();
        textView.setText(sentence.getRandomSentence());
        textView.startAnimation(AnimationUtils.loadAnimation(mainscreen.this, R.anim.abc_slide_in_top));
    }

    public void openHistory() {
        Intent intent = new Intent(this, ShowHistoryActivity.class);
        intent.putStringArrayListExtra(EXTRA_ARRAYLIST, sentence.getSentenceHistory());
        startActivity(intent);
    }
}
