using Newtonsoft.Json;
using RestSharp;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace tipClient
{
    public partial class Form1 : Form
    {

        public Form1()
        {
            InitializeComponent();
        }

        //Afiseaza
        private void button1_Click(object sender, EventArgs e)
        {
            var client = new RestClient("http://localhost:1234/WebServices/rest/DbAccess/get");
            var request = new RestRequest(Method.GET);
            request.AddHeader("Postman-Token", "cce63857-d11f-4531-ac44-78035e08054b");
            request.AddHeader("Cache-Control", "no-cache");

            IRestResponse response = client.Execute(request);
            textBox1.Text = response.Content;

            string parsat = "";
            Newtonsoft.Json.Linq.JObject x = (Newtonsoft.Json.Linq.JObject)JsonConvert.DeserializeObject(response.Content);
            Newtonsoft.Json.Linq.JToken jt = x["produse"];
            Newtonsoft.Json.Linq.JToken jt2 = jt[0];
            //for (int i = 0; i < jt.Count(); i++)
            foreach(Newtonsoft.Json.Linq.JToken j in jt)
            {
                parsat +=
                j["denumire"].ToString() + " " +
                j["pret"].ToString() + " " +
                j["producator"].ToString() + "\r\n";
            }
            textBox1.Text = parsat;
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        //Adauga
        private void button2_Click(object sender, EventArgs e)
        {
            var client = new RestClient("http://localhost:1234/WebServices/rest/DbAccess/post");
            var request = new RestRequest("", RestSharp.Method.POST);
            request.AddParameter("denumire", textBox2.Text);
            request.AddParameter("pret", textBox3.Text);
            request.AddParameter("producator", textBox4.Text);
            var response = client.Execute(request);

            if (response.IsSuccessful)
                MessageBox.Show("yeees");
            else
                MessageBox.Show("nu a mers");
        }

        //Modifica
        private void button3_Click(object sender, EventArgs e)
        {
            var client = new RestClient("http://localhost:1234/WebServices/rest/DbAccess/put");
            var request = new RestRequest("", RestSharp.Method.PUT);
            request.AddParameter("id", textBox5.Text);
            request.AddParameter("denumire", textBox6.Text);
            request.AddParameter("pret", textBox7.Text);
            request.AddParameter("producator", textBox8.Text);
            var response = client.Execute(request);

            if (response.IsSuccessful)
                MessageBox.Show("yeees");
            else
                MessageBox.Show("nu a mers");
        }

        private void textBox9_TextChanged(object sender, EventArgs e)
        {

        }

        private void button4_Click(object sender, EventArgs e)
        {
            var client = new RestClient("http://localhost:1234");
            var request = new RestRequest("/WebServices/rest/DbAccess/delete/4", Method.DELETE);
            //request.AddParameter("id", 4);
            var response = client.Execute(request);

            
            if (response.IsSuccessful)
                MessageBox.Show("yeees");
            else
                MessageBox.Show("maluma baby");
        }
    }
}
