package de.homelabs.rfilesystems.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;

@Controller
@RequestMapping("createDropboxToken.html")
public class DropBoxTokenizerController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView createAuthKey(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// Get your app key and secret from the Dropbox developers website.
		final String APP_KEY = "0wr35zzbytsief0";
		final String APP_SECRET = "5008c8olr8y0nci";
		String AUTH_CODE = null;

		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig("homelabstest", Locale
				.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

		// Have the user sign in and authorize your app.
		String authorizeUrl = webAuth.start();
		model.put("authorizeUrl", authorizeUrl);
		return new ModelAndView("dropboxtokenizer", model);
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public ModelAndView createDropboxToken(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @RequestParam("authcode") String authCode) throws DbxException {

		final String APP_KEY = "0wr35zzbytsief0";
		final String APP_SECRET = "5008c8olr8y0nci";
		String AUTH_CODE = authCode;
		
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

		DbxRequestConfig config = new DbxRequestConfig("homelabstest",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		DbxAuthFinish authFinish = webAuth.finish(AUTH_CODE);
		model.put("accessToken", authFinish.accessToken);
		
		//now test token
		String accessToken = "xAr0bNdyCSwAAAAAAAAAAUX5jqaPudzQ4KKjCvomMinn_XSO0yikECEFqz2VaoNN";
		DbxRequestConfig config_new = new DbxRequestConfig("homelabstest",
				Locale.getDefault().toString());
		
		DbxClient client = new DbxClient(config_new, accessToken);
		String linked_account = client.getAccountInfo().displayName;
		model.put("linkedAccount", linked_account);
		return new ModelAndView("dropboxtokenizer_finish", model);
	}
}
