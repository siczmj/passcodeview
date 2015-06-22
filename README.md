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

#### Wrap mode or Stretch mode

The PasscodeView working as auto wrapping mode and stretch mode too. What does it mean? In wrap mode
the PasscodeView measure self by children sizes. It's good when you set the buttons size wrap_content
or any exact size. In this case you need to use appropriate *inflater* in getView() implementation.
See any [example of PasscodeViewAdapter](https://github.com/siczmj/passcodeview/tree/master/passcodeview-example/src/main/java/com/nirigo/mobile/passcode/examples/adapters).
If you set the layout_width and/or layout_height of PasscodeView to match_parent or exact size then
will be stretch each child with equal size. So, in this case not matter what is the size of the
children.

<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_wrap_mode.png" width="200" />
<img src="https://github.com/siczmj/passcodeview/blob/master/screenshots/passcode_view_stretch_mode.png" width="200" />


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

Add the passcode xml namespace to access all attributes:

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:passcode="http://schemas.android.com/apk/res-auto"
    ...
```

Add the indicator to xml and give the appearance parameters:

```xml
<com.nirigo.mobile.view.passcode.PasscodeIndicator
        android:id="@+id/passcode_indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"
        android:layout_marginTop="24dp"
        passcode:indicator_background="@color/custom_gray"
        passcode:indicator_background_active="@color/custom_green"
        passcode:indicator_length="6"
        passcode:indicator_level="2"
        passcode:indicator_margin="7dp"
        passcode:indicator_size="12dp"
        passcode:indicator_animation="@anim/custom_indicator_animation"
        />
```



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

[COMING SOON]


------

## TODO

- change number of column
- add selector support
- add watch() method to passcodeindicator to listening EditText/Autocomplete..
- example for imageview implementation


## License
See the LICENSE file in the project root.
