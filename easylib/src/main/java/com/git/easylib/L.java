package com.git.easylib;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 打印log的类, 会调用系统的log, 将调用该log的类名作为tag, 并且自动在log信息中添加方法信息和行信息.<br/>
 * 如果需要使用自定义Tag的Log, 需要调用 {@link #vt(String, String)} |
 * {@link #dt(String, String)} 方法.<br/>
 * <br/>
 * 如果需要打印Log, 只需要调用 {@link #enableDebug()} 方法.(默认为打印Log) <br/>
 * 如果需要关闭打印Log, 只需要调用 {@link #disableDebug()} 方法.(默认为打印Log) <br/>
 * <br/>
 * 如果需要将Log保存到文件中, 只需要调用 {@link #enableFileLog(Context)} 方法.(默认不将Log保存到文件中) <br/>
 * 如果需要取消将Log保存到文件中, 只需要调用 {@link #disableFileLog()} 方法.(默认不将Log保存到文件中) <br/>
 * 
 * <pre>
 *   如果应用程序的Application是继承自{@link BaseApplication}, 那么久不需要做特殊处理.
 *   否则的话, 推荐使用方式:
 *   在Application的onCreate方法中添加如下函数. 这样在打混淆包的时候, 不需要做额外的处理, 会自动取消打印Log.
 *   if(!BuildConfig.DEBUG){//如果为非debug模式
 *       L.disableDebug();
 *   }
 * 
 * </pre>
 * 
 * @author Kingt
 * 
 */
public class L {
	private static boolean DEBUG = true;
	private static boolean LOG_ON_FILE = false;
	private static String packageName = "";

	private static String sClassName = null;
	private static String sMethodName = null;
	private static Toast mToast;
	

	private static void getTrace() {
		StackTraceElement caller = new Throwable().fillInStackTrace()
				.getStackTrace()[2];

		String className = new StringBuilder().append(caller.getClassName())
				.toString();
		sClassName = className.substring(className.lastIndexOf(".") + 1);
		sMethodName = new StringBuilder().append(
				caller.getMethodName() + "->" + caller.getLineNumber() + ": ")
				.toString();
	}
//toast
	public static void Toast(Context context, String msg) {
		if (!DEBUG)
			return;
		getTrace();
		String message = sClassName + "->" + sMethodName;
		if (mToast == null) {
			mToast = Toast.makeText(context, msg+"==="+message, 2000);
		} else {
			mToast.setText(msg);
		}
		mToast.show();
	}
//toast.end
	public static void v(String text) {
		if (!DEBUG)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.v(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.VERBOSE, sClassName, message);
	}

	public static void v(String text, Object... args) {
		if (!DEBUG)
			return;

		try {
			text = String.format(text, args);
		} catch (RuntimeException e) {

		}

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.v(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.VERBOSE, sClassName, message);
	}

	public static void vt(String tag, String text) {
		if (!DEBUG)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.v(tag, message);
		if (LOG_ON_FILE)
			logOnFile(Log.VERBOSE, tag, message);
	}

	/**
	 * 调用系统的Log, 将调用该方法的类名作为tag, 并且自动在log信息中添加方法信息和行信息.
	 * 
	 * @param text
	 *            需要打印的Log信息
	 */
	public static void d(String text) {
		if (!DEBUG)
			return;

		getTrace();
		String message = sClassName + "->" + sMethodName + text;
		Log.d(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.DEBUG, sClassName, message);
	}

	/**
	 * 
	 * 调用系统的Log, 将调用该方法的类名作为tag, 并且自动在log信息中添加方法信息和行信息. 如果格式化出错, 不会抛出异常,
	 * 但是只会将text本身打印出来<br/>
	 * <br/>
	 * 
	 * 示例:
	 * 
	 * <pre>
	 * L.d(&quot;我要打印一个字符串:%s 打印一个整数:%d&quot;, &quot;这是一个字符串&quot;, 100);
	 * 则在LogCat的输出信息是:"我要打印一个字符串:这是一个字符串 打印一个整数:100"
	 * </pre>
	 * 
	 * @param text
	 *            需要打印的Log信息, 可以使用%s %d %f等格式化类型
	 * @param args
	 *            %s %d %f等的格式化数据
	 * @see String#format(String, Object...)
	 */
	public static void d(String text, Object... args) {
		if (!DEBUG)
			return;

		try {
			text = String.format(text, args);
		} catch (RuntimeException e) {

		}

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.d(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.DEBUG, sClassName, message);
	}

	/**
	 * 使用自定义Tag打印Log, 并且但是会自动在输出日志前添加方法信息和行信息.
	 * 
	 * @param tag
	 *            自定义的Tag
	 * @param text
	 *            要打印的内容
	 */
	public static void dt(String tag, String text) {
		if (!DEBUG)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.d(tag, message);
		if (LOG_ON_FILE)
			logOnFile(Log.DEBUG, tag, message);
	}

	public static void i(String text) {
		if (!DEBUG)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.i(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.INFO, sClassName, message);
	}

	public static void i(String text, Object... args) {
		if (!DEBUG)
			return;

		try {
			text = String.format(text, args);
		} catch (RuntimeException e) {

		}

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.i(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.INFO, sClassName, message);
	}

	public static void w(String text) {
		if (!DEBUG)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.w(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.WARN, sClassName, message);
	}

	public static void w(String text, Object... args) {
		if (!DEBUG)
			return;

		try {
			text = String.format(text, args);
		} catch (RuntimeException e) {

		}

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.w(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.WARN, sClassName, message);
	}

	public static void e(String text) {
		if (!DEBUG)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.e(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.ERROR, sClassName, message);
	}

	public static void e(String text, Object... args) {
		if (!DEBUG)
			return;

		try {
			text = String.format(text, args);
		} catch (RuntimeException e) {

		}

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.e(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.ERROR, sClassName, message);

	}

	public static void e(Throwable e) {
		if (!DEBUG)
			return;

		if (e == null)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + e.toString();
		Log.e(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.ERROR, sClassName, message);
	}

	public static void e(Throwable e, String text) {
		if (!DEBUG)
			return;

		if (e == null)
			return;

		getTrace();

		String message = sClassName + "->" + sMethodName + text;
		Log.e(sClassName, message);
		if (LOG_ON_FILE)
			logOnFile(Log.ERROR, sClassName, sClassName);
	}

	private static void logOnFile(int level, String tag, String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		String time = sdf.format(new Date());
		FileWriter writer = null;
		try {
			File logFile = new File(getCachedFolder() + "log.log");
			if (logFile.exists() && logFile.length() > 100 * 1024) {// 大于100KB
				logFile.renameTo(new File(getCachedFolder() + "log_" + time
						+ ".log"));
			}

			writer = new FileWriter(logFile, true);
			writer.write(time + "/" + level + "/" + tag + "/" + text);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {

			}
		}

	}

	/**
	 * 获取Log缓存路径
	 * 
	 * @return Log缓存路径
	 */
	private static String getCachedFolder() {
		String path;
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {// 有SD卡
//			path = Environment.getExternalStorageDirectory().getPath()
//					+ "/Android/data/" + packageName + "/log/";
//		} else
		{
			path = Environment.getDataDirectory().getPath() + "/data/"
					+ packageName + "/log/";
		}

		File file = new File(path);
		if (!file.isDirectory()) {
			file.mkdirs();
		}

		return path;
	}

	/**
	 * 打开调试模式
	 */
	public static void enableDebug() {
		DEBUG = true;
	}

	/**
	 * 关闭调试模式
	 */
	public static void disableDebug() {
		DEBUG = false;
	}

	/**
	 * 允许将Log保存到文件中(默认不保存到文件中)
	 */
	public static void enableFileLog(Context context) {
		LOG_ON_FILE = true;

		if (context != null)
			packageName = context.getPackageName();
	}

	/**
	 * 取消将Log保存到文件中(默认不保存到文件中)
	 */
	public static void disableFileLog() {
		LOG_ON_FILE = false;
	}
}
