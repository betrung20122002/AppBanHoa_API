using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using Project_Do_an_4.Models;

namespace Project_Do_an_4.Controllers.API
{
    public class ProductDetailsController : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: api/ProductDetails
        public IQueryable<ProductDetails> GetProductDetails()
        {
            return db.ProductDetails;
        }
        
        // GET: api/ProductDetails/5
        [ResponseType(typeof(ProductDetails))]
        public IHttpActionResult GetProductDetails(int id)
        {
            ProductDetails productDetails = db.ProductDetails.Find(id);
            if (productDetails == null)
            {
                return NotFound();
            }

            return Ok(productDetails);
        }

        // PUT: api/ProductDetails/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutProductDetails(int id, ProductDetails productDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != productDetails.Id_productdetails)
            {
                return BadRequest();
            }

            db.Entry(productDetails).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ProductDetailsExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/ProductDetails
        [ResponseType(typeof(ProductDetails))]
        public IHttpActionResult PostProductDetails(ProductDetails productDetails)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.ProductDetails.Add(productDetails);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = productDetails.Id_productdetails }, productDetails);
        }

        // DELETE: api/ProductDetails/5
        [ResponseType(typeof(ProductDetails))]
        public IHttpActionResult DeleteProductDetails(int id)
        {
            ProductDetails productDetails = db.ProductDetails.Find(id);
            if (productDetails == null)
            {
                return NotFound();
            }

            db.ProductDetails.Remove(productDetails);
            db.SaveChanges();

            return Ok(productDetails);
        }

        [HttpGet]
        [Route("api/chon/Getall_chon/{id}")]
        public IHttpActionResult Getall_chon(string id)
        {
            var chon = db.ProductDetails.Where(x => x.Size == id);
            if (!chon.Any())
            {
                return NotFound();
            }
            return Ok(chon);

        }
        [HttpGet]
        [Route("api/chon/TimKiemTheoKhoangGia/{minPrice}/{maxPrice}")]
        public IHttpActionResult Getall_chon( int minPrice, int maxPrice)
        {
            var chon = db.ProductDetails.Where(x => x.Price >= minPrice && x.Price <= maxPrice);
            if (!chon.Any())
            {
                return NotFound();
            }
            return Ok(chon);
        }
        // GET: api/ProductDetails/5
        [HttpGet]
        [Route("api/ProductDetails/GetProductDetailsSize/{Size}")]
        public IHttpActionResult GetProductIDSize(String Id_product)
        {
            var productDetail = db.ProductDetails.Where(x => x.Size == Id_product);
            if (!productDetail.Any())
            {
                return NotFound();
            }

            return Ok(productDetail);
        }
        // GET: api/ProductDetails/5
        [HttpGet]
        [Route("api/ProductDetails/GetProductDetails/{Id_product}")]
        public IHttpActionResult GetProductIDDetails(int Id_product)
        {
            var productDetail = db.ProductDetails.Where(x => x.Id_product == Id_product);
            if (!productDetail.Any())
            {
                return NotFound();
            }

            return Ok(productDetail);
        }
      
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ProductDetailsExists(int id)
        {
            return db.ProductDetails.Count(e => e.Id_productdetails == id) > 0;
        }
    }
}