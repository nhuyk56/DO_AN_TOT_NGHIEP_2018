using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
namespace WebApplication2.YNNSHOP56131778.CONGFIG
{
    public class connect
    {

        //private string csdl = @"Data Source=EC2AMAZ-QCMKC82\SQLEXPRESS;Initial Catalog=ynn_shop;user=Nhuyk56;pwd=@Nhuyk56";
        private string csdl = @"Data Source=DESKTOP-BD4AA9G\SQLEXPRESS;Initial Catalog=ynn_shop;user=Nhuyk56;pwd=@Nhuyk56";
        //private string csdl = @"Data Source=DESKTOP-BD4AA9G\SQLEXPRESS;Initial Catalog=ynn_shop;Integrated Security=True";
        public string getconnect()
        {
            return csdl;
        }
    }
}