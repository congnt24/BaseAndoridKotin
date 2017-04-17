# BaseAndoridKotin
A base module for every android application. It base on MVP architect combine with Retrofit, RX and Dagger2
## Getting Started
To implement, just download/clone to your computer and import module "kotlinmvp" to your android project
## How to use
To using, sure that your android project can run kotlin code. If you don't know how to config android project for using kotlin, read [this article](https://medium.com/@elye.project/setup-kotlin-for-android-studio-1bffdf1362e8) for more information.
## This repo contain?
### 1. Navigator
Normally, in your android project, you used to have so many Activity. Every screen is an activity.It's bored and hard to manage.

The idea behing Navigator is we will use Fragment instead Activity. It's mean we will have 1 Activity, and all other screen is Fragment
-> Activity control all fragment -> easy to passing data between fragments

To open a fragment just call
```java
 awesomeNavigation!!.openFragment(R.id.content_frame, FirstFragment(), tag="FirstFragment")
```
To back to previous fragment just call
```java
 awesomeNavigation!!.goback()
```
To clear all fragment
```java
 awesomeNavigation!!.clear()
```
To go back specific fragment 
```java
 awesomeNavigation!!.goToFragment(tag="FirstFragment")
```
#### Example
##### Activity
```java
class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun initialize() {
        setSupportActionBar(toolbar)
        btn_showdrawer.setOnClickListener { awesomeNavigation!!.openFragment(R.id.content_frame, FirstFragment(), tag="FirstFragment") }
    }
}
```
##### Fragment
```java
class FirstFragment : BaseFragment(R.layout.first_fragment){
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener { activity.awesomeNavigation!!.openFragment(R.id.content_frame, SecondFragment(), tag = "SecondFragment") }
    }
}
```
### 2. AwesomeSharedPreferences
With this class you won't need to specific every boilerplate method for saving data in sharedpreference.
Every thing you need is create your own SharedPreference suck as: MyPrefs and extends AwesomeSharedPreferences.
Here is how to using it
```java
object MyPrefs : AwesomeSharedPreferences() {
    var isFirst = object : SimpleSharedPreferences<Boolean>(id = "is_first"){}
}
```
```java
    MyPrefs.initialize(this)
    MyPrefs.isFirst.load(defaultT = false)
```
### 3. Utilities
```java
// OS Utils
hideSoftKeyboard(activity: Activity)
showSoftKeyboard(view: View)
toggleKeyBoard(context: Context)
isAppInstalled(context: Context, packageName: String)
getAppVersion(context: Context)
openPlayStore(context: Context, packageName: String)
//Network
getOkHttpClient(...)
getRetrofit(baseUrl, ...)
//Communication
sendSms(context: Context, phoneNumber: String, message: String)//send sms to someone
dialTo(context: Context, phoneNumber: String)// Dial to someone
openMailApp(context: Context, dialogTitle: String, email: String, subject: String, text: String)// open email application
shareAll(context: Context, dialogTitle: String, content: String)//share to others application
//Sound
playSound(context: Context, idRaw: Int)
playSoundRepeat(context: Context, idRaw: Int)
stop()
setStreamMute(context: Context, isMute: Boolean)
//Convert
"abcabc".to64()// convert to base 64
"abcabc".from64()// convert from base 64 to string
"abcabc".tomd5()// convert to md5
"abcabc".toSHA1()// convert to sha-1
12f.dpToPx(context: Context)   // convert dp to px
pxToDp, spToPx, pxToSp
//Check pattern
"trungcong24@gmail.com".checkMatchPattern(
  Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
) //Return true
```
### 4. RxBus
EventBus by RxJava, you can define your own EventBus by create class RxBus:
```java

object RxBus {
    private val bus = PublishSubject.create<Any>()
    fun post(event: Any) {
        bus.onNext(event)
    }

    fun <T> events(type: Class<T>): Observable<T> {
        return bus.ofType(type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun hasObservable(): Boolean {
        return bus.hasObservers()
    }
}
```
How to use RxBus
1. To Post event
```java
RxBus.post("hello world") // send String data to all subscriber
```
2. To Subscribe
```java
RxBus.events(String::class.java)
                .subscribe { tv_result.text = it }  // Register for String type, when receive a string, tv_result will change value
```

.....

