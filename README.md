# PasscodeView
If you need a Passcode view (like PIN code or iOS Passcode) then there's a good widget. The basic
concept is a library that contains two seperated views.

#### 1.) PasscodeView
This is a full customizable numeric pad interface. Easy to use for lock screen, PIN pad, login or
other security reason. You can add new button or change the button style using PasscodeBaseAdapter.

#### 2.) PasscodeIndicator
An simple indicator view to feedback for user when type on the PasscodeView. You can place anywhere
in the layout XML independent PasscodeView. It is also highly customizable with XML attributes like
padding, margin, default or active drawable and of course animation.

### Screenshots
<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_example_plain.png" width="175" />
<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_example_customized.png" width="175" />
<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_example_ios.png" width="175" />
<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_example_android.png" width="175" />

### Demo
<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_android_capture.gif" width="250" height="403" />

------

## Usage

### Download
There is enough to download only the passcodeview-library which contains all neccessary files.

### Add PasscodeView to your XML layout
```xml
<com.nirigo.mobile.view.passcode.PasscodeView
        android:id="@+id/passcode_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
```

#### Wrap mode or Strech mode


### Implement PasscodeBaseAdapter ...
```java
public class CustomPasscodeAdapter extends PasscodeBaseAdapter {

    private LayoutInflater inflater;

    public CustomPasscodeAdapter(Context context) {
        super(Arrays.asList(
                new PasscodeItem("1", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("2", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("3", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("4", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("5", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("6", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("7", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("8", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("9", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("X", PasscodeItem.TYPE_CLEAR),
                new PasscodeItem("0", PasscodeItem.TYPE_NUMBER),
                new PasscodeItem("<", PasscodeItem.TYPE_REMOVE)
        ));
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PasscodeItem item = getItem(position);

        if(convertView == null || convertView.getTag() != PasscodeItem.class){
            convertView = inflater.inflate(R.layout.button_passcode_custom, parent, false);
            convertView.setTag(PasscodeItem.class);
        }

        AppCompatButton button = (AppCompatButton) convertView;
        button.setText(item.getValue());
        button.setVisibility(item.getType() == PasscodeItem.TYPE_EMPTY ? View.INVISIBLE : View.VISIBLE);

        return convertView;

    }


}
```

#### ... then make an instance and set it!
```java
    CustomPasscodeAdapter adapter = new CustomPasscodeAdapter(getActivity());
    passcodeView.setAdapter(adapter);
```


### Add the PasscodeIndicator to your XML layout




------

## Tips & Tricks

### How to add custom buttons and actions to PasscodeView?

It's really easy. You can see the example in the Android implementation:

1. You need to extends the PasscodeItem model and add the new parameters.
like PasscodeItemAndroid and PasscodeItemAndroidImage in the models package

2. Make new adapter that use your unique PasscodeItems.
like AndroidPasscodeAdapter

3. Handle it in the getView() implementation and pass your custom layout.
like in the AndroidPasscodeAdapter.getView() I added Enter event with ImageView

4. Catch the onItemClick and hanle the custom event.
like in the ExampleAndroidFragment, check Enter event.


### Can I use any drawable/mipmap/color on indicator?

Yes.

------

## Sample



------

## TODO

- change number of column
- add watch() method to passcodeindicator to listening EditText/Autocomplete..
- example for imageview implementation


## License
See the LICENSE file in the project root.
