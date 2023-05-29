using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Project_Do_an_4.Models;

namespace Project_Do_an_4.Controllers.Client
{
    public class order_Controller : Controller
    {
        private Projcet_ban_hoaaEntities2 db = new Projcet_ban_hoaaEntities2();

        // GET: order_
        public ActionResult Index()
        {
            return View(db.order_.ToList());
        }

        // GET: order_/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            order_ order_ = db.order_.Find(id);
            if (order_ == null)
            {
                return HttpNotFound();
            }
            return View(order_);
        }

        // GET: order_/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: order_/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id_Order,Id_Account,Id_productdetails,Quantity,TotalMoney,Message,PaymentMethod,NgayDat,Status,Notes")] order_ order_)
        {
            if (ModelState.IsValid)
            {
                db.order_.Add(order_);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(order_);
        }

        // GET: order_/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            order_ order_ = db.order_.Find(id);
            if (order_ == null)
            {
                return HttpNotFound();
            }
            return View(order_);
        }

        // POST: order_/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id_Order,Id_Account,Id_productdetails,Quantity,TotalMoney,Message,PaymentMethod,NgayDat,Status,Notes")] order_ order_)
        {
            if (ModelState.IsValid)
            {
                db.Entry(order_).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(order_);
        }

        // GET: order_/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            order_ order_ = db.order_.Find(id);
            if (order_ == null)
            {
                return HttpNotFound();
            }
            return View(order_);
        }

        // POST: order_/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            order_ order_ = db.order_.Find(id);
            db.order_.Remove(order_);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
