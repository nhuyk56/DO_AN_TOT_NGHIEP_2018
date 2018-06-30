using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.QuanTri
{
    public class maHoa
    {
        public static string RemoveQueryStringByKey(string url, string key)
        {
            var uri = new Uri(url);

            // this gets all the query string key value pairs as a collection
            var newQueryString = HttpUtility.ParseQueryString(uri.Query);

            // this removes the key if exists
            newQueryString.Remove(key);

            // this gets the page path from root without QueryString
            string pagePathWithoutQueryString = uri.GetLeftPart(UriPartial.Path);

            return newQueryString.Count > 0
                ? String.Format("{0}?{1}", pagePathWithoutQueryString, newQueryString)
                : pagePathWithoutQueryString;
        }
           public string Base64Encode(string plainText) {
          var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(plainText);
          return System.Convert.ToBase64String(plainTextBytes);
        }

        public string Base64Decode(string base64EncodedData) {
          try{
              var base64EncodedBytes = System.Convert.FromBase64String(base64EncodedData);
          return System.Text.Encoding.UTF8.GetString(base64EncodedBytes);
          }
            catch(Exception e){
                try
                {
                    var base64EncodedBytes = System.Convert.FromBase64String(base64EncodedData + "==");
                    return System.Text.Encoding.UTF8.GetString(base64EncodedBytes);
                }
                catch (Exception x) {
                    var base64EncodedBytes = System.Convert.FromBase64String(base64EncodedData + "=");
                    return System.Text.Encoding.UTF8.GetString(base64EncodedBytes);
                }
            }
        }
    }
}