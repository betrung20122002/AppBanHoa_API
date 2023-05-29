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
    public class CartsController : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: api/Carts
        public IQueryable<Cart> GetCart()
        {
            return db.Cart;
        }
        [HttpGet]
        [Route("api/Cart/GetCartAccpunt/{id_account}")]
        public IHttpActionResult GetCartAccpunt(int id_account)
        {
            var cart = db.Cart.Where(x => x.Id_Account == id_account);
            if (!cart.Any())
            {
                return NotFound();
            }
            return Ok(cart);
        }

        // GET: api/Carts/5
        [ResponseType(typeof(Cart))]
        public IHttpActionResult GetCart(int id)
        {
            Cart cart = db.Cart.Find(id);
            if (cart == null)
            {
                return NotFound();
            }

            return Ok(cart);
        }

        // PUT: api/Carts/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCart(int id, Cart cart)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != cart.Id_Cart)
            {
                return BadRequest();
            }

            db.Entry(cart).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CartExists(id))
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
       
        // POST: api/Carts
        [ResponseType(typeof(Cart))]
        public IHttpActionResult PostCart(Cart cart)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Cart.Add(cart);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = cart.Id_Cart }, cart);
        }
        [HttpGet]
        [Route("api/Cart/TotalMoney/{id_account}")]
        public IHttpActionResult GetTotalMoney(int id_account)
        {
            var cart = db.Cart.Where(x => x.Id_Account == id_account).Sum(x => x.TotalMoney);

            if (cart == null)
            {
                return NotFound();
            }
            return Ok(cart);
        }

        // DELETE: api/Carts/5
        [ResponseType(typeof(Cart))]
        public IHttpActionResult DeleteCart(int id)
        {
            Cart cart = db.Cart.Find(id);
            if (cart == null)
            {
                return NotFound();
            }

            db.Cart.Remove(cart);
            db.SaveChanges();

            return Ok(cart);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool CartExists(int id)
        {
            return db.Cart.Count(e => e.Id_Cart == id) > 0;
        }
    }
}