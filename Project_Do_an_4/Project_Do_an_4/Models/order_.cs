//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Project_Do_an_4.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class order_
    {
        public int Id_Order { get; set; }
        public Nullable<int> Id_Account { get; set; }
        public Nullable<int> Id_productdetails { get; set; }
        public int Quantity { get; set; }
        public int TotalMoney { get; set; }
        public string Message { get; set; }
        public string PaymentMethod { get; set; }
        public DateTime NgayDat { get; set; }
        public Nullable<int> Status { get; set; }
        public string Notes { get; set; }
    }
}
