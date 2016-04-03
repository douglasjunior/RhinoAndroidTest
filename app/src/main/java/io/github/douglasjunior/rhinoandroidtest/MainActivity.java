package io.github.douglasjunior.rhinoandroidtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        executeScript();
    }

    private void executeScript() {
        String source = "" +
                "game.walk();\n" +
                "game.walk();\n" +
                "var jumps = 10;\n" +
                "for (var i = 0; i < jumps; i++) {\n" +
                "   game.jump(i + 1);\n" +
                "}";

        org.mozilla.javascript.Context cx = org.mozilla.javascript.Context.enter();
        cx.setOptimizationLevel(-1); // disable compilation
        Scriptable scope = cx.initStandardObjects();
        Object wrappedOut = cx.javaToJS(this, scope);
        ScriptableObject.putProperty(scope, "game", wrappedOut);
        Object result = cx.evaluateString(scope, source, "<cmd>", 1, null);

    }

    public void walk() {
        System.out.println("Walking!");
    }

    public void jump(int count) {
        System.out.println("Jumping! " + count);
    }
}
