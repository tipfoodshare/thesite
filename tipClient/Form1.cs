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

    }
}
