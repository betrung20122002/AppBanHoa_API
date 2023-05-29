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
    public class TinTucsController : ApiController
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: api/TinTucs
        public IQueryable<TinTuc> GetTinTuc()
        {
            return db.TinTuc;
        }

        [HttpGet]
        [Route("api/getall")]
        public IQueryable<TinTuc> Getdanhmuc_sp()
        {
            return db.TinTuc;
        }

        // GET: api/TinTucs/5
        [ResponseType(typeof(TinTuc))]
        public IHttpActionResult GetTinTuc(int id)
        {
            TinTuc tinTuc = db.TinTuc.Find(id);
            if (tinTuc == null)
            {
                return NotFound();
            }

            return Ok(tinTuc);
        }

        // PUT: api/TinTucs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTinTuc(int id, TinTuc tinTuc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tinTuc.MaTT)
            {
                return BadRequest();
            }

            db.Entry(tinTuc).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TinTucExists(id))
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

        // POST: api/TinTucs
        [ResponseType(typeof(TinTuc))]
        public IHttpActionResult PostTinTuc(TinTuc tinTuc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.TinTuc.Add(tinTuc);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tinTuc.MaTT }, tinTuc);
        }

        // DELETE: api/TinTucs/5
        [ResponseType(typeof(TinTuc))]
        public IHttpActionResult DeleteTinTuc(int id)
        {
            TinTuc tinTuc = db.TinTuc.Find(id);
            if (tinTuc == null)
            {
                return NotFound();
            }

            db.TinTuc.Remove(tinTuc);
            db.SaveChanges();

            return Ok(tinTuc);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TinTucExists(int id)
        {
            return db.TinTuc.Count(e => e.MaTT == id) > 0;
        }
    }
}