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
    public class order_Controller : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: api/order_
        public IQueryable<order_> Getorder_()
        {
            return db.order_;
        }
        [HttpGet]
        [Route("api/Getorder/{id_Account}/{Status}")]
        public IHttpActionResult Getorder(int id_Account, int Status)
        {
            var order_ = db.order_.Where(x => x.Id_Account == id_Account && x.Status == Status);
            if (!order_.Any())
            {
                return NotFound();
            }

            return Ok(order_);
        }

        // GET: api/order_/5
        [ResponseType(typeof(order_))]
        public IHttpActionResult Getorder_(int id)
        {
            order_ order_ = db.order_.Find(id);
            if (order_ == null)
            {
                return NotFound();
            }

            return Ok(order_);
        }

        // PUT: api/order_/5
        [ResponseType(typeof(void))]
        public IHttpActionResult Putorder_(int id, order_ order_)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != order_.Id_Order)
            {
                return BadRequest();
            }

            db.Entry(order_).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!order_Exists(id))
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
        [HttpPut]
        [Route("api/Putorder/{id}")]
        public IHttpActionResult Putchon(int id, order_ order)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != order.Id_Order)
            {
                return BadRequest();
            }

            db.Entry(order).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!order_Exists(id))
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
        // POST: api/order_
        [ResponseType(typeof(order_))]
        public IHttpActionResult Postorder_(order_ order_)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.order_.Add(order_);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = order_.Id_Order }, order_);
        }

        // DELETE: api/order_/5
        [ResponseType(typeof(order_))]
        public IHttpActionResult Deleteorder_(int id)
        {
            order_ order_ = db.order_.Find(id);
            if (order_ == null)
            {
                return NotFound();
            }

            db.order_.Remove(order_);
            db.SaveChanges();

            return Ok(order_);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool order_Exists(int id)
        {
            return db.order_.Count(e => e.Id_Order == id) > 0;
        }
    }
}