using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT.van_chuyen
{
    public class Datum
    {
        public string contentId { get; set; }
        public string applicationDate { get; set; }
        public string logo { get; set; }
        public string @checked { get; set; }
        public string carrierName { get; set; }
        public string carrierId { get; set; }
        public string serviceName { get; set; }
        public string serviceId { get; set; }
        public string serviceType { get; set; }
        public string serviceTypeName { get; set; }
        public string serviceDescription { get; set; }
        public long estimatedDeliveryTime { get; set; }
        public long totalCod { get; set; }
        public long mainShipFee { get; set; }
        public string routeTypeId { get; set; }
        public long shipFee { get; set; }
        public long codFee { get; set; }
        public long returnFee { get; set; }
        public long mainShipFeePromotion { get; set; }
        public long shipFeePromotion { get; set; }
        public long codFeePromotion { get; set; }
        public long shipFeeDiscount { get; set; }
        public long codFeeDiscount { get; set; }
        public List<object> namePromotion { get; set; }
        public long totalFee { get; set; }
        public long customerShipFee { get; set; }
        public long sender { get; set; }
        public long receiver { get; set; }
        public long setting { get; set; }
        public long idSetting { get; set; }
        public long fromCity { get; set; }
        public long weight { get; set; }
        public long toCity { get; set; }
        public object isBulkyGoods { get; set; }
    }
}