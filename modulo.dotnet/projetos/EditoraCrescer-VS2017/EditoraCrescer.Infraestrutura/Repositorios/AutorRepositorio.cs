﻿using EditoraCrescer.Infraestrutura.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Repositorios
{
    public class AutorRepositorio
    {
        private Contexto contexto = new Contexto();

        public AutorRepositorio()
        { }

        public List<Autor> Obter()
        {
            return contexto.Autores.ToList();
        }

        public Autor Obter(int id)
        {
            return contexto.Autores.FirstOrDefault(x => x.Id == id);
        }

        public List<Livro> ObterLivros(int id)
        {
            return contexto.Livros.Where(x => x.IdAutor == id).ToList();
        }

        public Autor Criar(Autor autor)
        {
            var autorCriado = contexto.Autores.Add(autor);
            contexto.SaveChanges();
            return autorCriado;
        }

        public bool Excluir(int id)
        {
            var livro = contexto.Autores.FirstOrDefault(x => x.Id == id);
            if (livro == null)
            {
                return false;
            }
            else
            {
                contexto.Autores.Remove(livro);
                contexto.SaveChanges();
                return true;
            }
        }
    }
}
