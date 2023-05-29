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
    public class NotifiesController : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: api/Notifies
        public IQueryable<Notify> GetNotify()
        {
            return db.Notify;
        }

        // GET: api/Notifies/5
        [ResponseType(typeof(Notify))]
        public IHttpActionResult GetNotify(int id)
        {
            Notify notify = db.Notify.Find(id);
            if (notify == null)
            {
                return NotFound();
            }

            return Ok(notify);
        }

        // PUT: api/Notifies/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutNotify(int id, Notify notify)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != notify.Id_notify)
            {
                return BadRequest();
            }

            db.Entry(notify).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NotifyExists(id))
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
        [HttpGet]
        [Route("api/Notifies/{Id_Account}")]
        public IHttpActionResult GetNotifies(int Id_Account)
        {
            var notify = db.Notify.Where(x => x.Id_Account == Id_Account && x.watched == 0);
            if (!notify.Any())
            {
                return NotFound();
            }

            return Ok(notify);
        }

        // POST: api/Notifies
        [ResponseType(typeof(Notify))]
        public IHttpActionResult PostNotify(Notify notify)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Notify.Add(notify);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = notify.Id_notify }, notify);
        }

        // DELETE: api/Notifies/5
        [ResponseType(typeof(Notify))]
        public IHttpActionResult DeleteNotify(int id)
        {
            Notify notify = db.Notify.Find(id);
            if (notify == null)
            {
                return NotFound();
            }

            db.Notify.Remove(notify);
            db.SaveChanges();

            return Ok(notify);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool NotifyExists(int id)
        {
            return db.Notify.Count(e => e.Id_notify == id) > 0;
        }
    }
}