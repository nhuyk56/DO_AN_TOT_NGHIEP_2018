using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.YNNSHOP56131778.OBJECT
{
    public class c2_group
    {
        public List<c2_group_item> c2_group_3i { get; set; }
        public c2_group(List<c2_group_item> c2_group_3i) { this.c2_group_3i = c2_group_3i; }

        public List<c2_group_item> getc2_group_3i()
        {
            return c2_group_3i;

        }
        public void setc2_group_3i(List<c2_group_item> c2_group_3i)
        {
            this.c2_group_3i = c2_group_3i;
        }
    }
}