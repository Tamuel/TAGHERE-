package practce1.nfcpractice.nfcpractice;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    ToggleButton tglReadWrtie;
    EditText txtTagContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //onNewIntent(getIntent());
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(!(nfcAdapter != null && nfcAdapter.isEnabled()))
            Toast.makeText(this,"Nfc adapter is unavailable",Toast.LENGTH_LONG).show();

        tglReadWrtie = (ToggleButton)findViewById(R.id.tglReadWrite);
        txtTagContent = (EditText)findViewById(R.id.txtTagContent);

    }

    //put a priority to this activity for NFC
    private void enableForegroundDispatchSystem(){
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] intentFilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this,pendingIntent,intentFilters,null);


    }
    private void disalbeForegroundDispatchSystem(){

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Toast.makeText(this,"NFC Intent received!",Toast.LENGTH_LONG).show();

        if(tglReadWrtie.isChecked())        //read
        {
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if(parcelables != null && parcelables.length>0)
            {
                readTextFromMessage((NdefMessage) parcelables[0]);
            }
            else
            {
                Toast.makeText(this,"No NDEF message found!",Toast.LENGTH_LONG).show();
            }
        }
        else {                              //write
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage ndefMessage = createNdefMessage(txtTagContent.getText()+""); //content should be here.

            wirteNdefMessage(tag, ndefMessage);
        }

    }

    private void readTextFromMessage(NdefMessage ndefMessage) {
        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if(ndefRecords != null && ndefRecords.length>0){

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            txtTagContent.setText(tagContent);
        }
        else{
            Toast.makeText(this,"No NDEF records found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {

        enableForegroundDispatchSystem();

        super.onResume();
    }

    @Override
    protected void onPause() {

        nfcAdapter.disableForegroundNdefPush(this);

        disalbeForegroundDispatchSystem();

        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void formatTag(Tag tag, NdefMessage ndefMessage)
    {
        try{
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null)
            {
                Toast.makeText(this,"Tag is not ndef formidable!",Toast.LENGTH_LONG).show();
                return;
            }
            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();

            Toast.makeText(this,"Tag Written",Toast.LENGTH_LONG).show();

        }
        catch(Exception e)
        {
            Log.e("FormatTag",e.getMessage());
        }
    }

    private void wirteNdefMessage(Tag tag,NdefMessage ndefMessage)
    {
        try{

            if(tag == null)
            {
                Toast.makeText(this,"Tag object cannot be null",Toast.LENGTH_LONG).show();
                return;
            }

            Ndef ndef =  Ndef.get(tag);

            //What is the difference betw writing data into ndefformatable and writing data into ndef???
            //Here is what I expect, what ndef is null means, ndef with the tag isn't available for unaccepted format.
            //otherwise, it can be written without a help with ndefformatted....

            if(ndef == null)
            {
                //format tag with the ndef format and writes the message
                formatTag(tag, ndefMessage);
            }
            else
            {
                ndef.connect();

                if(!ndef.isWritable())
                {
                    Toast.makeText(this,"Tag is not writable",Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return;
                }

                ndef.writeNdefMessage(ndefMessage);
                ndef.close();

                Toast.makeText(this,"Tag Written",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
        }
    }

    //tranfrom the data formatted
    private NdefRecord createTextRecord(String content)
    {
        try {
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = content.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte) (languageSize & 0x1F));
            payload.write(language,0,languageSize);
            payload.write(text,0,textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN,NdefRecord.RTD_TEXT,new byte[0], payload.toByteArray());

        }catch(UnsupportedEncodingException e){
            Log.e("createTextRecord",e.getMessage());
        }
        return null;
    }

    private NdefMessage createNdefMessage(String content){

        NdefRecord ndefRecord = createTextRecord(content);

        NdefMessage ndefMessage = new NdefMessage((new NdefRecord[]{ndefRecord}));

        return ndefMessage;
    }
    public void tglReadWriteOnclick(View view)
    {
        txtTagContent.setText("");
    }

    public String getTextFromNdefRecord(NdefRecord ndefRecord)
    {
        String tagContent = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String textEncoding;
            if((payload[0] & 128) == 0) {
                textEncoding = "UTF-8";
            }
            else {
                textEncoding = "UTF-16";
            }
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1,
                    payload.length - languageSize - 1, textEncoding);
        }catch (UnsupportedEncodingException e)
        {
            Log.e("getTextFromNdefRecord", e.getMessage(),e);
        }
        return tagContent;
    }

}
