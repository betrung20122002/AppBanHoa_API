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
    
    public partial class Cart
    {
        public int Id_Cart { get; set; }
        public Nullable<int> Id_Account { get; set; }
        public Nullable<int> Id_productdetails { get; set; }
        public Nullable<int> Quantity { get; set; }
        public Nullable<int> TotalMoney { get; set; }
        public string Notes { get; set; }
    }
}