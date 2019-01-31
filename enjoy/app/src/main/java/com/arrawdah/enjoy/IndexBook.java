package com.arrawdah.enjoy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class IndexBook extends AppCompatActivity {

    private ArrayList<String> array1 = new ArrayList<String>();
    private ArrayList<Integer> array2 = new ArrayList<Integer>();

    private ListView list_index;
    TextView textIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_book);

        list_index = (ListView) findViewById(R.id.listIndex);
        textIndex = (TextView) findViewById(R.id.textIndex);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SultanNahia_HelpMacNet_.ttf");

        textIndex.setTypeface(face);

        if (Build.VERSION.SDK_INT >=21) {
            //color StatusBar
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#5c9ace"));
        }

        array1.add("مدخل");
        array1.add("هؤلاء لن يستفيدوا");
        array1.add("ماذا سنتعلم");
        array1.add("لماذا نبحث عن المهارات");
        array1.add("طور نفسك");
        array1.add("لا تبك على اللبن المسكوب");
        array1.add("كن متميزاً");
        array1.add("أي الناس أحب إليك");
        array1.add("استمتع بالمهارات");
        array1.add("مع الفقراء");
        array1.add("النساء");
        array1.add("الصغار");
        array1.add("الخدم والمماليك");
        array1.add("مع المخالفين");
        array1.add("الحيوانات !!");
        array1.add("100 طريقة لكسب قلوب الناس");
        array1.add("أحسن النية .. لوجه الله");
        array1.add("استعمل الطعم المناسب !!");
        array1.add("اختر الكلام المناسب");
        array1.add("كم لطيفاً عند أول لقاء");
        array1.add("الناس كمعادن الأرض");
        array1.add("شعرة معاوية");
        array1.add("مفاتيح القلوب");
        array1.add("مراعاة النفسيات");
        array1.add("اهتم بالآخرين");
        array1.add("أشعرهم أنك تحب الخير لهم");
        array1.add("إحفظ الأسماء");
        array1.add("كن لماحاً");
        array1.add("انتبه : كن لماحاً للجمال فقط");
        array1.add("لا تتدخل فيما لا يعنيك");
        array1.add("كيف تتعامل مع (الملاقيف) حشريين ؟");
        array1.add("لا تنتقد !!");
        array1.add("لا تكن أستاذياً !!");
        array1.add("أمسك العصا من النصف !!");
        array1.add("اجعل معالجة الخطأ سهلة");
        array1.add("الرأي الآخر");
        array1.add("قابل الإساءة بالإحسان");
        array1.add("اقنعه بخطئه ليقبل النصح");
        array1.add("لا تلمني !! انتهى الأمر");
        array1.add("تأكد من الخطأ قبل النصيحة");
        array1.add("اجلدني برفق");
        array1.add("فر من المشاكل !!");
        array1.add("اعترف بخطئك .. لا تكابر");
        array1.add("مفاتيح الأخطاء !");
        array1.add("فكك الحزمة");
        array1.add("جلد الذات !!");
        array1.add("مشاكل ليس لها حل");
        array1.add("لا تقتل نفسك بالهم");
        array1.add("ارض بما قسم الله لك");
        array1.add("كن جبلاً");
        array1.add("لا تلعنه .. إنه يشرب خمراً ..!!");
        array1.add("إذا لم يكن ما تريد فأرد ما يكون ..!!");
        array1.add("نختلف ونحن إخوان !");
        array1.add("الرفق .. إلا زانه");
        array1.add("بين الحي .. والميت");
        array1.add("اجعل لسانك عذباً");
        array1.add("اختصر .. ولا تجادل");
        array1.add("لا تبال كلام الخلق");
        array1.add("ابتسم .. ثم ابتسم .. ثم ابتسـ .. ثم أبتــ");
        array1.add("الخطوط الحمر");
        array1.add("حفظ السر");
        array1.add("قضاء الحاجات");
        array1.add("لا تتكلف مالا تطيق !!");
        array1.add("من ركل القطة ؟!");
        array1.add("التواضع");
        array1.add("العبادة الخفية");
        array1.add("أخرجهم من الحفرة");
        array1.add("الاهتمام بالمظهر");
        array1.add("الصدق");
        array1.add("الشجاعة");
        array1.add("الثبات على المبادئ");
        array1.add("إغراءات");
        array1.add("العفو عن الآخرين");
        array1.add("الكرم");
        array1.add("كف الأذى");
        array1.add("لا للعداوات");
        array1.add("اللسان .. ملك !!");
        array1.add("اضبط لسانك");
        array1.add("المفتاح");
        array1.add("الرصيد العاطفي");
        array1.add("الساحر");
        array1.add("فليسعد النطق إن لم تسعد الحال !!");
        array1.add("الدعاء");
        array1.add("الترقيع !!");
        array1.add("انظر بعينين");
        array1.add("فن الاستماع");
        array1.add("فن الحوار");
        array1.add("اقطع الطريق على المعترضين");
        array1.add("انتظر .. لا تعترض ..!!");
        array1.add("قبل نجواكم .. صدقة");
        array1.add("ليس مهماً أن تنجح دائماً");
        array1.add("كن بطلاً وابدأ الآن");

        array2.add(3);
        array2.add(5);
        array2.add(8);
        array2.add(10);
        array2.add(14);
        array2.add(18);
        array2.add(21);
        array2.add(24);
        array2.add(32);
        array2.add(36);
        array2.add(38);
        array2.add(43);
        array2.add(47);
        array2.add(50);
        array2.add(58);
        array2.add(61);
        array2.add(65);
        array2.add(69);
        array2.add(82);
        array2.add(90);
        array2.add(96);
        array2.add(108);
        array2.add(113);
        array2.add(115);
        array2.add(120);
        array2.add(131);
        array2.add(135);
        array2.add(137);
        array2.add(143);
        array2.add(146);
        array2.add(150);
        array2.add(153);
        array2.add(158);
        array2.add(165);
        array2.add(172);
        array2.add(181);
        array2.add(187);
        array2.add(194);
        array2.add(200);
        array2.add(212);
        array2.add(216);
        array2.add(219);
        array2.add(225);
        array2.add(230);
        array2.add(237);
        array2.add(243);
        array2.add(248);
        array2.add(250);
        array2.add(253);
        array2.add(259);
        array2.add(262);
        array2.add(264);
        array2.add(266);
        array2.add(270);
        array2.add(279);
        array2.add(290);
        array2.add(297);
        array2.add(300);
        array2.add(303);
        array2.add(306);
        array2.add(310);
        array2.add(316);
        array2.add(320);
        array2.add(325);
        array2.add(332);
        array2.add(334);
        array2.add(340);
        array2.add(342);
        array2.add(345);
        array2.add(348);
        array2.add(350);
        array2.add(354);
        array2.add(357);
        array2.add(365);
        array2.add(372);
        array2.add(376);
        array2.add(378);
        array2.add(384);
        array2.add(388);
        array2.add(394);
        array2.add(397);
        array2.add(405);
        array2.add(413);
        array2.add(426);
        array2.add(430);
        array2.add(434);
        array2.add(439);
        array2.add(445);
        array2.add(448);
        array2.add(451);
        array2.add(459);
        array2.add(461);



        final CustomListIndexBook adapter1 = new
                CustomListIndexBook(IndexBook.this, array1,array2);

        list_index.setAdapter(adapter1);



        list_index.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                String itemValue = (String) list_index.getItemAtPosition(position);
                //   Toast.makeText(getApplicationContext(), itemValue, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IndexBook.this, ReaderHtml.class);
                //Start details activity
                int numberPage = 461 - array2.get(position);
                Log.i("numberPage: " + numberPage, "");
                intent.putExtra("numberPassImg", numberPage);
                intent.putExtra("numberPassCheck", true);
                startActivity(intent);


            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
