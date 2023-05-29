using Project_Do_an_4.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Project_Do_an_4.Controllers.API
{
    public class ThongKeController : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();
        [HttpGet]
        [Route("api/revenue/{ngay}/{thang}/{nam}")]
        public IHttpActionResult TongDoanhThuTheoNgay(int  ngay,int thang, int nam )
        {
            int tongtien = 0;
            //lấy ra những đơn hàng có ngày tương ứng
            var list = db.order_.Where(n => n.NgayDat.Day == ngay  && n.NgayDat.Month == thang  && n.NgayDat.Year == nam  && n.Status == 2);
            //duyệt chi tiết của đơn hàng và lấy ra tổng tiền của đơn hàng đó
            foreach (var x in list)
            {
                tongtien += x.TotalMoney;
            }
            return Ok(tongtien);
        }
        // Thống kê tài khoản này đã mua đc bao nhiêu tiền
        [HttpGet]
        [Route("api/revenue/{Account}")]
        public IHttpActionResult ThongKeTheoTaiKhoan(int Account)
        {
            int tong = 0;
            //lấy ra những đơn hàng có ngày tương ứng
            var list = db.order_.Where(n => n.Id_Account == Account && n.Status == 2);
            //duyệt chi tiết của đơn hàng và lấy ra tổng tiền của đơn hàng đó
            foreach (var x in list)
            {
                tong += x.TotalMoney;
            }
            return Ok(tong);
        }
        // Thống kê số lượng đơn hàng đã mua 
        [HttpGet]
        [Route("api/ThongKeSoLuong/{Account}")]
        public IHttpActionResult ThongKeSoLuong(int Account)
        {
            int tong = 0;
            //lấy ra những đơn hàng có ngày tương ứng
            var list = db.order_.Where(n => n.Id_Account == Account && n.Status == 2);
            //duyệt chi tiết của đơn hàng và lấy ra tổng tiền của đơn hàng đó
            foreach (var x in list)
            {
                tong += x.Quantity;
            }
            return Ok(tong);
        }
        [HttpGet]
        [Route("tongtheothang/{tentg}")]
        public IHttpActionResult getMasp(int  tentg)
        {
            int g = tentg;
            List<order_> dsin = db.order_.ToList();
            int tong = 0;
            int tong2 = 0;
            int tong3 = 0;
            int tong4 = 0;
            int tong5 = 0;
            List<order_> dsin1 = db.order_.ToList();
            foreach (order_ ls in dsin)
            {
                if (ls.NgayDat.Month == g)
                {
                    foreach (order_ l in dsin1)
                    {
                        if ( ls.Status == 2)
                        {
                            tong  += l.TotalMoney;
                        }
                    }
                }
                if (ls.NgayDat.Month == (g - 1))
                {
                    foreach (order_ l in dsin1)
                    {
                        if (l.Status == 2)
                        {
                            tong += l.TotalMoney;
                        }
                    }
                }
                if (ls.NgayDat.Month == (g - 2))
                {
                    foreach (order_ l in dsin1)
                    {
                        if (l.Status == 2)
                        {
                            tong += l.TotalMoney;
                        }
                    }
                }
                if (ls.NgayDat.Month == (g - 3))
                {
                    foreach (order_ l in dsin1)
                    {
                        if (l.Status == 2)
                        {
                            tong += l.TotalMoney;
                        }
                    }
                }
                if (ls.NgayDat.Month == (g - 4))
                {
                    foreach (order_ l in dsin1)
                    {
                        if (l.Status == 2)
                        {
                            tong += l.TotalMoney;
                        }
                    }
                }

            }
            return Ok("/" + tong + "/" + tong2 + "/" + tong3 + "/" + tong4 + "/" + tong5 + "/");
        }
        [HttpGet]
        [Route("thongkemax")]
        public List<string> GetMax()
        {
            List<string> lt = new List<string>();
            List<order_> dsin = db.order_.ToList();
            List<ProductDetails> dsin2 = db.ProductDetails.ToList();
            List<Product> dsin3 = db.Product.ToList();

            foreach (order_ ls in dsin)
            {
                int tong = 0;
                int soluong = 0;

                var orderDetails = dsin2.Where(od => od.Id_productdetails == ls.Id_productdetails);

                foreach (ProductDetails ls2 in orderDetails)
                {
                    var product = dsin3.FirstOrDefault(p => p.Id_product == ls2.Id_product);

                    if (product != null)
                    {
                        tong += ls.TotalMoney;
                        soluong += ls.Quantity;

                        string kp = product.Id_product + "/" + soluong + "/" + tong;
                        lt.Add(kp);
                    }
                }
            }

            return lt;
        }
        [HttpGet]
        [Route("api/TongHangDaBan")]
        public IHttpActionResult TongHangDaBan()
        {
            int tongdonhang = db.order_.Count(n => n.Status == 2);
            
            return Ok(tongdonhang);
        }
        [HttpGet]
        [Route("api/TongDoanhThu")]
        public IHttpActionResult TongDoanhThu()
        {
            decimal tongtien = 0;
            var getdh = db.order_.Where(s => s.Status == 2);
            foreach (var x in getdh)
            {
                tongtien += x.TotalMoney;
            }
            return Ok(tongtien);
        }
        

    }
}
