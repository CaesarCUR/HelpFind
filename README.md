# HelpFind
> A platform for `Lost and Found`

**Coding Regular**

1. We should always notice that coding style cannot tired other readers

	**WRONG**

	```java
	SharedPreferences pref=getSharedPreferences("location",MODE_PRIVATE );
	...
	Toast.makeText(mContext,"message",Toast.LENGTH_SHORT ).show();
	```
	**TRUE**
	```java
	SharedPreferences pref = getSharedPreferences("location", MODE_PRIVATE);
	...
	Toast.makeText(mContext, "message", Toast.LENGTH_SHORT).show();
	```
2. Rules for naming variable

	Naming varible in your `.java` file should follow [Camel-Case](), and its combine like: `[Action][Name]`

3. Each different method should spacing one line, for a example

	```java
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: execute sign up successful logic at here
                // by default, we just finish this activity
                this.finish();
            }
        } else if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
		// super.onBackPressed();
        moveTaskToBack(true);
    }
	```

4. *If-clause* should use folding style

	```java
	if (condition1) {
		statements;
	} else if (condition2) {
		statements;
	}
	```

5. In a certain method, if it has some different sub logic blocks, we should always notice that spacing at least one line between each sub-block

	```java
	protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastsquare);
        ButterKnife.bind(this);

        _broadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement broad text action
            }
        });

        _broadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement broad photo distribute action
            }
        });

        _btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open map
            }
        });
    }
	```

6. Make comments for your method which has complex sub functions or will be used as a important part. And we can make full uses in **Android Studio**, you can type `/**` + `enter` to produce comment model automatic

**Aditional advice**

All of you can download [ButterKnife](https://github.com/JakeWharton/butterknife/blob/master/README.md) to reduce your coding work