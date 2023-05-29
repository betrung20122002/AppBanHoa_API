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
    public class MessegesController : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: api/Messeges
        public IQueryable<Messege> GetMessege()
        {
            return db.Messege;
        }

        // GET: api/Messeges/5
        [ResponseType(typeof(Messege))]
        public IHttpActionResult GetMessege(int id)
        {
            Messege messege = db.Messege.Find(id);
            if (messege == null)
            {
                return NotFound();
            }

            return Ok(messege);
        }
        //
       

        // PUT: api/Messeges/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutMessege(int id, Messege messege)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != messege.Id_TinNhan)
            {
                return BadRequest();
            }

            db.Entry(messege).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MessegeExists(id))
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

        // POST: api/Messeges
        [ResponseType(typeof(Messege))]
        public IHttpActionResult PostMessege(Messege messege)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Messege.Add(messege);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = messege.Id_TinNhan }, messege);
        }

        // DELETE: api/Messeges/5
        [ResponseType(typeof(Messege))]
        public IHttpActionResult DeleteMessege(int id)
        {
            Messege messege = db.Messege.Find(id);
            if (messege == null)
            {
                return NotFound();
            }

            db.Messege.Remove(messege);
            db.SaveChanges();

            return Ok(messege);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool MessegeExists(int id)
        {
            return db.Messege.Count(e => e.Id_TinNhan == id) > 0;
        }
    }
}